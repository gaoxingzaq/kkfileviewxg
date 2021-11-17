package cn.keking.service.impl;

import cn.keking.config.ConfigConstants;
import cn.keking.model.FileAttribute;
import cn.keking.model.ReturnResponse;
import cn.keking.service.FilePreview;
import cn.keking.utils.DownloadUtils;
import cn.keking.service.FileHandlerService;
import cn.keking.web.filter.BaseUrlFilter;
import com.itextpdf.text.pdf.PdfReader;
import jodd.util.StringUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Collections;
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
    @Override
    public String filePreviewHandle(String url, Model model, FileAttribute fileAttribute) {
        String gengxin=fileAttribute.getgengxin();
        String fileName = fileAttribute.getName();
        String officePreviewType = fileAttribute.getOfficePreviewType();
        String baseUrl = BaseUrlFilter.getBaseUrl();
        String pdfName = fileName.substring(0, fileName.lastIndexOf(".") + 1) + "pdf";
        String outFilePath = FILE_DIR + pdfName;
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
                     pdfName =baseUrl+"download?urlPath="+pdfName;
                    }

                    model.addAttribute("pdfUrl", pdfName);
                }
            } else {

                if( pdffy.equalsIgnoreCase("false")){

                }else {
                  //  url= FileHandlerService.zhuanyii(url); //文件名转义
                 url =baseUrl+"download?urlPath="+url;
                }
                model.addAttribute("pdfUrl",url);
            }
        }
        return PDF_FILE_PREVIEW_PAGE;
    }
}
