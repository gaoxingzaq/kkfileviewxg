package cn.keking.service.impl;

import cn.keking.config.ConfigConstants;
import cn.keking.model.FileAttribute;
import cn.keking.service.FileHandlerService;
import cn.keking.service.FilePreview;
import cn.keking.web.filter.BaseUrlFilter;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.io.File;

/**
 * Content :处理OFD文件
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
    @Override
    public String filePreviewHandle(String url, Model model, FileAttribute fileAttribute) {
        String baseUrl = BaseUrlFilter.getBaseUrl();
        String outFilePath;
        String  host = FileHandlerService.hqurl(url);
        boolean bendi = FileHandlerService.kuayu(host, baseUrl); //判断是否是本地URL 是本地的启用分页功能 不是就直接在跨域输出
                    if(!bendi){
                        model.addAttribute("pdfUrl",url);
                        return OFD_FILE_PREVIEW_PAGE;
                    }else {
                        outFilePath = FILE_DIR +url.replace(baseUrl, "");  //本地URL 不下载去掉ULR 组合成本地路径
                        File filee = new File(outFilePath);   //判断文件是否存在
                        if(!filee.exists() || filee.length() == 0) {
                            return otherFilePreview.notSupportedFile(model, fileAttribute, "文件不存在");
                        }
                        String geshi =FileHandlerService.geshi(outFilePath,1);// 获取文件头信息
                        if (geshi.equals(".ofd")){
                            model.addAttribute("pdfUrl",url);
                            return OFD_FILE_PREVIEW_PAGE;
                        }else if (geshi.equals(".pdf")){
                            model.addAttribute("pdfUrl",url);
                            return PDF_FILE_PREVIEW_PAGE;
                        }else {
                            return otherFilePreview.notSupportedFile(model, fileAttribute, "文件错误或者其他类型,"+ geshi );
                        }
                    }
            }
    }

