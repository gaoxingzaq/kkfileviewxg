package cn.keking.service.impl;

import cn.keking.config.ConfigConstants;
import cn.keking.model.FileAttribute;
import cn.keking.model.ReturnResponse;
import cn.keking.service.FilePreview;
import cn.keking.utils.DownloadUtils;
import cn.keking.service.FileHandlerService;
import cn.keking.utils.WjtTypeUtils;
import cn.keking.web.filter.BaseUrlFilter;
import com.itextpdf.text.pdf.PdfReader;
import jodd.util.StringUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.List;

/**
 * Created by kl on 2018/1/17.
 * Content :处理pdf文件
 */
@Service
public class OfdFilePreviewImpl implements FilePreview {

    private final FileHandlerService fileHandlerService;
    private final OtherFilePreviewImpl otherFilePreview;
    private static final String FILE_DIR = ConfigConstants.getFileDir();

    public OfdFilePreviewImpl(FileHandlerService fileHandlerService, OtherFilePreviewImpl otherFilePreview) {
        this.fileHandlerService = fileHandlerService;
        this.otherFilePreview = otherFilePreview;
    }

    public static boolean kuayu(String host, String wjl) {  //查询域名是否相同
        if (wjl.contains(host)) {
            return true;
        }else {
            return false;
        }
    }
    @Override
    public String filePreviewHandle(String url, Model model, FileAttribute fileAttribute) {
        String fileName = fileAttribute.getName();
        String baseUrl = BaseUrlFilter.getBaseUrl();
        String pdfName = fileName.substring(0, fileName.lastIndexOf(".") + 1) + "ofd";
        java.net.URL urls = null;
        try {
            urls = new java.net.URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        String host = urls.getHost()+ ":" + urls.getDefaultPort();// 获取http信息
        boolean bendi = kuayu(host, baseUrl); //判断是否是本地URL 是本地的启用分页功能 不是就直接在跨域输出
                    if(!bendi){
                        model.addAttribute("pdfUrl",url);
                        return OFD_FILE_PREVIEW_PAGE;
                    }else {
                        String  geshi = null;
                        try {
                            geshi = WjtTypeUtils.getPicType(new FileInputStream(FILE_DIR + "demo/"+ pdfName));
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        if (geshi == ".ofd"  ){
                            model.addAttribute("pdfUrl",url);
                            return OFD_FILE_PREVIEW_PAGE;
                        }else if ( geshi == ".pdf" ){
                            model.addAttribute("pdfUrl",url);
                            return PDF_FILE_PREVIEW_PAGE;
                        }else {
                            return otherFilePreview.notSupportedFile(model, fileAttribute, "文件错误或者其他类型,"+ geshi );
                        }
                    }

            }
    }

