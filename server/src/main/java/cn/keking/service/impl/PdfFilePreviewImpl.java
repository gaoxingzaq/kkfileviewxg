package cn.keking.service.impl;

import cn.keking.config.ConfigConstants;
import cn.keking.model.FileAttribute;
import cn.keking.model.ReturnResponse;
import cn.keking.service.FileHandlerService;
import cn.keking.service.FilePreview;
import cn.keking.utils.DownloadUtils;
import cn.keking.utils.WjtTypeUtils;
import cn.keking.web.filter.BaseUrlFilter;
import jodd.util.StringUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.List;

/**
 * Created by kl on 2018/1/17.
 * Content :处理pdf文件
 */
@Service
public class PdfFilePreviewImpl implements FilePreview {

    private final FileHandlerService fileHandlerService;
    private final OtherFilePreviewImpl otherFilePreview;
    private static final String FILE_DIR = ConfigConstants.getFileDir();

    public PdfFilePreviewImpl(FileHandlerService fileHandlerService, OtherFilePreviewImpl otherFilePreview) {
        this.fileHandlerService = fileHandlerService;
        this.otherFilePreview = otherFilePreview;
    }
    @Value("${pdffy:false}")
    private String pdffy;
    @Value("${pdfpagee:0}")
    private String pdfpagee;
    public static boolean kuayu(String host, String wjl) {  //查询域名是否相同
        if (wjl.contains(host)) {
            return true;
        }else {
            return false;
        }
    }
    @Override
    public String filePreviewHandle(String url, Model model, FileAttribute fileAttribute) {
        String gengxin=fileAttribute.getgengxin();
        String fileName = fileAttribute.getName();
        String officePreviewType = fileAttribute.getOfficePreviewType();
        String baseUrl = BaseUrlFilter.getBaseUrl();
        String pdfName = fileName.substring(0, fileName.lastIndexOf(".") + 1) + "pdf";
        String outFilePath = FILE_DIR + pdfName;
        java.net.URL urls = null;
        try {
            urls = new java.net.URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        String host = urls.getHost()+ ":" + urls.getDefaultPort();// 获取http信息
        if (OfficeFilePreviewImpl.OFFICE_PREVIEW_TYPE_IMAGE.equals(officePreviewType) || OfficeFilePreviewImpl.OFFICE_PREVIEW_TYPE_ALL_IMAGES.equals(officePreviewType)) {
            //当文件不存在时，就去下载
            boolean pdfgx ;
            if(StringUtil.isNotBlank(gengxin) && "ok".equalsIgnoreCase(gengxin)) { //去缓存更新
                pdfgx= false;
            }else {
                pdfgx= ConfigConstants.isCacheEnabled();
            }
            if (!pdfgx ||!fileHandlerService.listConvertedFiles().containsKey(pdfName) || !ConfigConstants.isCacheEnabled()) {
                ReturnResponse<String> response = DownloadUtils.downLoad(fileAttribute, fileName);
                if (response.isFailure()) {
                    return otherFilePreview.notSupportedFile(model, fileAttribute, response.getMsg());
                }
                outFilePath = response.getContent();
                if (ConfigConstants.isCacheEnabled()) {
                    // 加入缓存
                    fileHandlerService.addConvertedFile(pdfName, fileHandlerService.getRelativePath(outFilePath));
                }
            }

            String  geshi = null;
            try {
                geshi = WjtTypeUtils.getPicType(new FileInputStream(outFilePath));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            if (geshi == ".pdf"  ){

            }else if ( geshi == ".ofd" ){
                model.addAttribute("pdfUrl",url);
                return OFD_FILE_PREVIEW_PAGE;
            }else {
                return otherFilePreview.notSupportedFile(model, fileAttribute, "文件错误或者其他类型,"+ geshi );
            }
            List<String> imageUrls = fileHandlerService.pdf2jpg(outFilePath, pdfName, baseUrl,fileAttribute);
            if (imageUrls == null || imageUrls.size() < 1) {
                return otherFilePreview.notSupportedFile(model, fileAttribute, "pdf转图片异常，请联系管理员");
            }
            model.addAttribute("imgurls", imageUrls);
            model.addAttribute("currentUrl", imageUrls.get(0));
            if (OfficeFilePreviewImpl.OFFICE_PREVIEW_TYPE_IMAGE.equals(officePreviewType)) {
                return OFFICE_PICTURE_FILE_PREVIEW_PAGE;
            } else {
                return PICTURE_FILE_PREVIEW_PAGE;
            }
        } else {
            // 不是http开头，浏览器不能直接访问，需下载到本地
            if (url != null && !url.toLowerCase().startsWith("http")) {
                if (!fileHandlerService.listConvertedFiles().containsKey(pdfName) || !ConfigConstants.isCacheEnabled()) {
                    ReturnResponse<String> response = DownloadUtils.downLoad(fileAttribute, pdfName);
                    if (response.isFailure()) {
                        return otherFilePreview.notSupportedFile(model, fileAttribute, response.getMsg());
                    }
                    model.addAttribute("pdfUrl", fileHandlerService.getRelativePath(response.getContent()));
                    if (ConfigConstants.isCacheEnabled()) {
                        // 加入缓存
                        fileHandlerService.addConvertedFile(pdfName, fileHandlerService.getRelativePath(outFilePath));
                    }
                } else {
                    pdfName= FileHandlerService.zhuanyii(pdfName);
                    if( pdffy.equalsIgnoreCase("false")){

                    }else {
                     pdfName= FileHandlerService.zhuanyii(pdfName); //文件名转义
                     pdfName =baseUrl+"download?urlPath="+pdfName+"?page="+pdfpagee;
                    }
                    model.addAttribute("pdfUrl", pdfName);
                }
            } else {

                if( pdffy.equalsIgnoreCase("false")){
                    boolean bendi = kuayu(host, baseUrl); //判断是否是本地URL 是本地的启用分页功能 不是就直接在跨域输出

                    if(!bendi){
                        model.addAttribute("pdfUrl",url);
                        return PDF_FILE_PREVIEW_PAGE;
                    }else {
                        String  geshi = null;
                        try {
                            geshi = WjtTypeUtils.getPicType(new FileInputStream(FILE_DIR + "demo/"+ pdfName));
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        if (geshi == ".pdf"  ){
                            model.addAttribute("pdfUrl",url);
                            return PDF_FILE_PREVIEW_PAGE;
                    }else if ( geshi == ".ofd" ){
                            model.addAttribute("pdfUrl",url);
                            return OFD_FILE_PREVIEW_PAGE;
                        }else {
                            return otherFilePreview.notSupportedFile(model, fileAttribute, "文件错误或者其他类型,"+ geshi );
                        }
                    }

                }else {
                    boolean bendi = kuayu(host, baseUrl); //判断是否是本地URL 是本地的启用分页功能 不是就直接在跨域输出
                    if(!bendi){
                        model.addAttribute("pdfUrl",url);
                        return PDF_FILE_PREVIEW_PAGE;
                    }else {
                        String  geshi = null;
                        try {
                            geshi = WjtTypeUtils.getPicType(new FileInputStream(FILE_DIR + "demo/"+ pdfName));
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        if (geshi == ".pdf"  ){
                            model.addAttribute("pdfUrl",url);
                            return PDF_FILE_PREVIEW_PAGE;
                        }else if ( geshi == ".ofd" ){
                            model.addAttribute("pdfUrl",url);
                            return OFD_FILE_PREVIEW_PAGE;
                        }else {
                            return otherFilePreview.notSupportedFile(model, fileAttribute, "文件错误或者其他类型,"+ geshi );
                        }
                    }
            }
        }

        return PDF_FILE_PREVIEW_PAGE;
    }
}
}
