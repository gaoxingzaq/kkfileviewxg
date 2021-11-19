package cn.keking.service.impl;

import cn.keking.config.ConfigConstants;
import cn.keking.model.FileAttribute;
import cn.keking.model.ReturnResponse;
import cn.keking.service.*;
import cn.keking.utils.DownloadUtils;
import cn.keking.web.filter.BaseUrlFilter;
import jodd.util.StringUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;

import java.net.URLEncoder;
import java.util.Collections;
import java.util.List;

/**
 * Created by kl on 2018/1/17.
 * Content :处理office文件
 */
@Service
public class OfficeFilePreviewImpl implements FilePreview {

    public static final String OFFICE_PREVIEW_TYPE_IMAGE = "image";
    public static final String OFFICE_PREVIEW_TYPE_ALL_IMAGES = "allImages";
    private static final String FILE_DIR = ConfigConstants.getFileDir();
    private final FileHandlerService fileHandlerService;
    private final OfficeToPdfService officeToPdfService;
    private final OtherFilePreviewImpl otherFilePreview;

    public OfficeFilePreviewImpl(FileHandlerService fileHandlerService, OfficeToPdfService officeToPdfService, OtherFilePreviewImpl otherFilePreview) {
        this.fileHandlerService = fileHandlerService;
        this.officeToPdfService = officeToPdfService;
        this.otherFilePreview = otherFilePreview;
    }
    @Value("${office.officexh:1}")
    private String officexh;
    @Value("${PPTXTP:1}")
    private int pptx;
    @Value("${pdffy:false}")
    private String pdffy;
    @Value("${officedel:true}")
    private String officedel;
    @Value("${xlsxzh:true}")
    private String xlsxzh;
    @Override
    public String filePreviewHandle(String url, Model model, FileAttribute fileAttribute) {
        // 预览Type，参数传了就取参数的，没传取系统默认
        String gengxin=fileAttribute.getgengxin();
        String officePreviewType = fileAttribute.getOfficePreviewType();
        String baseUrl = BaseUrlFilter.getBaseUrl();
        String suffix = fileAttribute.getSuffix();
        String fileName = fileAttribute.getName();
        String imagesss = FILE_DIR + fileName;
        boolean isHtml;
        boolean doc = suffix.equalsIgnoreCase("doc") || suffix.equalsIgnoreCase("docx") || suffix.equalsIgnoreCase("wps") || suffix.equalsIgnoreCase("dot") || suffix.equalsIgnoreCase("docm") || suffix.equalsIgnoreCase("vsd") ;
        boolean xls = suffix.equalsIgnoreCase("xls") || suffix.equalsIgnoreCase("xlsx") || suffix.equalsIgnoreCase("xlsm")  ;
        boolean ppt = suffix.equalsIgnoreCase("ppt") || suffix.equalsIgnoreCase("pptx");
        if (officexh.equals("1")) {
             isHtml = suffix.equalsIgnoreCase("xls") || suffix.equalsIgnoreCase("xlsx") || suffix.equalsIgnoreCase("xlsm") || suffix.equalsIgnoreCase("csv") ;
        }else {
             isHtml = suffix.equalsIgnoreCase("xls") || suffix.equalsIgnoreCase("xlsx") || suffix.equalsIgnoreCase("xlsm") || suffix.equalsIgnoreCase("csv") || suffix.equalsIgnoreCase("doc") || suffix.equalsIgnoreCase("docx") || suffix.equalsIgnoreCase("wps") || suffix.equalsIgnoreCase("ppt") || suffix.equalsIgnoreCase("pptx") || suffix.equalsIgnoreCase("dot") || suffix.equalsIgnoreCase("dotx") || suffix.equalsIgnoreCase("dotm")  || suffix.equalsIgnoreCase("vsd")  || suffix.equalsIgnoreCase("rtf")  ;
        }

        String pdfName = fileName.substring(0, fileName.lastIndexOf(".") + 1) + (isHtml ? "html" : "pdf");
        String ptxName = fileName.substring(0, fileName.lastIndexOf(".") + 1) + "file";
        String outFilePath = FILE_DIR + pdfName;
        // 判断之前是否已转换过，如果转换过，直接返回，否则执行转换
        boolean pdfgx ;
        if(StringUtil.isNotBlank(gengxin) && "ok".equalsIgnoreCase(gengxin)) { //去缓存更新
            pdfgx= false;
        }else {
            pdfgx= ConfigConstants.isCacheEnabled();
        }
        boolean xlsx =  suffix.equalsIgnoreCase("xlsx");

        if(officePreviewType.equalsIgnoreCase("xlsx") || xlsxzh.equalsIgnoreCase("true")){  //是否开启xlsx直接输出功能

            if(officePreviewType.equalsIgnoreCase("html")){

            }else if(officePreviewType.equalsIgnoreCase("xlsx")) {
                if(xlsx){
                    model.addAttribute("pdfUrl", url);
                    return XLSX_FILE_PREVIEW_PAGE;
                }
            }else {
                if(xlsx){
                    model.addAttribute("pdfUrl", url);
                    return XLSX_FILE_PREVIEW_PAGE;
                }
            }

        }
       // System.out.print(11);
        if (!pdfgx || !fileHandlerService.listConvertedFiles().containsKey(pdfName)) {
            String filePath;
            ReturnResponse<String> response = DownloadUtils.downLoad(fileAttribute, null);
            if (response.isFailure()) {
                return otherFilePreview.notSupportedFile(model, fileAttribute, response.getMsg());
            }
            filePath = response.getContent();
            if (officexh.equals("1")) {   //开源openoffice 或  LibreOffice转换
                if (StringUtils.hasText(outFilePath)) {
                officeToPdfService.openOfficeToPDF(filePath, outFilePath);
                    if(officedel.equalsIgnoreCase("false")){  //是否保留OFFICE源文件

                        FileHandlerService.deleteFile(filePath);
                    }

                if (isHtml) {
                    // 对转换后的文件进行操作(改变编码方式)
                  fileHandlerService.doActionConvertedFile(outFilePath);
                } } }else { //POI转换
                try {
                    if(doc){
                        if(POIWordToHtml.wordToHtml(filePath, imagesss, fileName, outFilePath)){
                        }else {
                            return otherFilePreview.notSupportedFile(model, fileAttribute, "文件错误或者其他，尝试其他文件");
                        }
                    }else if(xls){
                        if(POIExcelToHtml.excelToHtml(outFilePath,  filePath,"")){
                        }else {
                            return otherFilePreview.notSupportedFile(model, fileAttribute, "文件错误或者其他，尝试其他文件");
                        }
                    }else if(ppt){

                        if(POIPptToHtml.pptToHtml(filePath, ptxName,outFilePath, pptx)){
                        }else {
                            return otherFilePreview.notSupportedFile(model, fileAttribute, "文件错误或者其他，尝试其他文件");
                        }
                    }
                    if( officedel.equalsIgnoreCase("false")){  //是否保留OFFICE源文件
                        FileHandlerService.deleteFile(filePath);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (ConfigConstants.isCacheEnabled()) {
                // 加入缓存
                fileHandlerService.addConvertedFile(pdfName, fileHandlerService.getRelativePath(outFilePath));
            }
        }

        if (!isHtml && baseUrl != null && (OFFICE_PREVIEW_TYPE_IMAGE.equals(officePreviewType) || OFFICE_PREVIEW_TYPE_ALL_IMAGES.equals(officePreviewType))) {
            return getPreviewType(model, fileAttribute, officePreviewType, baseUrl, pdfName, outFilePath, fileHandlerService, OFFICE_PREVIEW_TYPE_IMAGE, otherFilePreview);
        }

        if(isHtml || pdffy.equalsIgnoreCase("false")){  //是否开启PDF分割功能

        }else {

          if(FileHandlerService.pdfpage(pdfName) <=1){  //判断PDF文件 当文件小于等于1就不进行分割
              pdfName= FileHandlerService.zhuanyii(pdfName); //文件名转义
          }else {
              pdfName= FileHandlerService.zhuanyii(pdfName);  //文件名转义
              pdfName ="download?urlPath="+pdfName;   //分割PDF文件
          }

        }

        model.addAttribute("pdfUrl", pdfName);
        return isHtml ? EXEL_FILE_PREVIEW_PAGE : PDF_FILE_PREVIEW_PAGE;
    }

    static String getPreviewType(Model model, FileAttribute fileAttribute, String officePreviewType, String baseUrl, String pdfName, String outFilePath, FileHandlerService fileHandlerService, String officePreviewTypeImage, OtherFilePreviewImpl otherFilePreview) {
        String suffix = fileAttribute.getSuffix();
        boolean isPPT = suffix.equalsIgnoreCase("ppt") || suffix.equalsIgnoreCase("pptx");
        List<String> imageUrls = fileHandlerService.pdf2jpg(outFilePath, pdfName, baseUrl, fileAttribute);
        if (imageUrls == null || imageUrls.size() < 1) {
            return otherFilePreview.notSupportedFile(model, fileAttribute, "office转图片异常，请联系管理员");
        }
        model.addAttribute("imgurls", imageUrls);
        model.addAttribute("currentUrl", imageUrls.get(0));
        if (officePreviewTypeImage.equals(officePreviewType)) {
            // PPT 图片模式使用专用预览页面
            return (isPPT ? PPT_FILE_PREVIEW_PAGE : OFFICE_PICTURE_FILE_PREVIEW_PAGE);
        } else {
            return PICTURE_FILE_PREVIEW_PAGE;
        }
    }
}
