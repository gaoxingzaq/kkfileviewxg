package cn.keking.service.impl;

import cn.keking.config.ConfigConstants;
import cn.keking.model.FileAttribute;
import cn.keking.model.ReturnResponse;
import cn.keking.service.FileHandlerService;
import cn.keking.service.FilePreview;
import cn.keking.utils.ConvertPicUtil;
import cn.keking.utils.DownloadUtils;
import cn.keking.utils.WebUtils;
import cn.keking.web.filter.BaseUrlFilter;
import jodd.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.List;
import java.util.regex.Pattern;

/**
 * tiff 图片文件处理
 * @author kl (http://kailing.pub)
 * @since 2021/2/8
 */
@Service
public class TiffFilePreviewImpl implements FilePreview {
    private final FileHandlerService fileHandlerService;
    private final OtherFilePreviewImpl otherFilePreview;
    private static final String FILE_DIR = ConfigConstants.getFileDir();

    public TiffFilePreviewImpl(FileHandlerService fileHandlerService, OtherFilePreviewImpl otherFilePreview) {
        this.fileHandlerService = fileHandlerService;
        this.otherFilePreview = otherFilePreview;
    }
    private static final String INITIALIZE_MEMORY_SIZE = "initializeMemorySize";
    //默认初始化 50MB 内存
    private static final long INITIALIZE_MEMORY_SIZE_VALUE_DEFAULT = 1024L * 1024 * 50;
    @Override
    public String filePreviewHandle(String url, Model model, FileAttribute fileAttribute) {
        String baseUrl = BaseUrlFilter.getBaseUrl();
        String gengxin=fileAttribute.getgengxin();
        String fileName = fileAttribute.getName();
        String regEx = "[`#%:;\"\\\\]";
        String fileNamee = Pattern.compile(regEx).matcher(fileName).replaceAll("").trim();
        String officePreviewType = fileAttribute.getOfficePreviewType();
        String pdfName =  fileNamee + "." +  "pdf";
        String outFilePath = FILE_DIR + pdfName;
        String tifoutFilePath;
        String  host = FileHandlerService.hqurl(url);
        boolean bendi = FileHandlerService.kuayu(host, baseUrl); //判断是否是本地URL 是本地的启用分页功能 不是就直接在跨域输出
        boolean pdfgx ;
        if(StringUtil.isNotBlank(gengxin) && "ok".equalsIgnoreCase(gengxin)) { //去缓存更新
            pdfgx= false;
        }else {
            pdfgx= ConfigConstants.isCacheEnabled();
        }
        String tifPreviewType = ConfigConstants.getTifPreviewType();
        if("tif".equalsIgnoreCase(tifPreviewType)){
            String fileSize = WebUtils.getUrlParameterReg(url,INITIALIZE_MEMORY_SIZE);
            if(StringUtils.hasText(fileSize)){
                model.addAttribute(INITIALIZE_MEMORY_SIZE,fileSize);
            }else {
                model.addAttribute(INITIALIZE_MEMORY_SIZE,Long.toString(INITIALIZE_MEMORY_SIZE_VALUE_DEFAULT));
            }
            return TIFF_FILE_PREVIEW_PAGE;
        }else if("pdf".equalsIgnoreCase(tifPreviewType)){
            if (!pdfgx ||!fileHandlerService.listConvertedFiles().containsKey(pdfName) || !ConfigConstants.isCacheEnabled()) {
                if(!bendi){
                    ReturnResponse<String> response = DownloadUtils.downLoad(fileAttribute, fileName);
                    if (response.isFailure()) {
                        return otherFilePreview.notSupportedFile(model, fileAttribute, response.getMsg());
                    }
                    tifoutFilePath = response.getContent();
                    String geshi =FileHandlerService.geshi(tifoutFilePath,0);// 获取文件头信息
                    if (geshi.equals(".tif") || geshi.equals(".tiff") ){
                        if(ConvertPicUtil.convertJpg2Pdf(tifoutFilePath, outFilePath)){

                        }else {
                            model.addAttribute("currentUrl", url);
                            return TIFF_FILE_PREVIEW_PAGE;
                        }
                    }else {
                        return otherFilePreview.notSupportedFile(model, fileAttribute, "文件类型不正确");
                    }

                }else {
                    tifoutFilePath = FILE_DIR +url.replace(baseUrl, "");  //本地URL 不下载去掉ULR 组合成本地路径
                    File filee = new File(tifoutFilePath);   //判断文件是否存在
                    if(!filee.exists() || filee.length() == 0) {
                        return otherFilePreview.notSupportedFile(model, fileAttribute, "文件不存在");
                    }
                    String geshi =FileHandlerService.geshi(tifoutFilePath,0);// 获取文件头信息
                    if (geshi.equals(".tif") || geshi.equals(".tiff") ){
                        if(ConvertPicUtil.convertJpg2Pdf(tifoutFilePath, outFilePath)){

                        }else {
                           model.addAttribute("currentUrl", url);
                            return TIFF_FILE_PREVIEW_PAGE;
                        }
                    }else {
                        return otherFilePreview.notSupportedFile(model, fileAttribute, "文件类型不正确");
                    }
                }
              //  System.out.println(tifoutFilePath);
            if (ConfigConstants.isCacheEnabled()) {
                // 加入缓存
                fileHandlerService.addConvertedFile(pdfName, fileHandlerService.getRelativePath(outFilePath));
            }
            }
            if ("pdf".equalsIgnoreCase(officePreviewType)) {
                model.addAttribute("pdfUrl", pdfName);
                return PDF_FILE_PREVIEW_PAGE;
            } else {
                List<String> imageUrls = fileHandlerService.pdf2jpg(outFilePath, pdfName, baseUrl,fileAttribute);
                if (imageUrls == null || imageUrls.size() < 1) {
                    return otherFilePreview.notSupportedFile(model, fileAttribute, "pdf转图片异常，请联系管理员");
                }
                model.addAttribute("imgurls", imageUrls);
                model.addAttribute("currentUrl", imageUrls.get(0));
                return OFFICE_PICTURE_FILE_PREVIEW_PAGE;
            }
        }
        return NOT_SUPPORTED_FILE_PAGE;
    }
}
