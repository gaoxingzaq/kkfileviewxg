package cn.keking.service.impl;

import cn.keking.config.ConfigConstants;
import cn.keking.model.FileAttribute;
import cn.keking.model.ReturnResponse;
import cn.keking.service.FilePreview;
import cn.keking.utils.DownloadUtils;
import cn.keking.service.FileHandlerService;
import cn.keking.utils.KkFileUtils;
import cn.keking.web.filter.BaseUrlFilter;
import jodd.util.StringUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;

import java.util.regex.Pattern;

import static cn.keking.service.impl.OfficeFilePreviewImpl.getPreviewType;

/**
 * @author chenjh
 * @since 2019/11/21 14:28
 */
@Service
public class CadFilePreviewImpl implements FilePreview {

    private static final String OFFICE_PREVIEW_TYPE_IMAGE = "image";
    private static final String OFFICE_PREVIEW_TYPE_ALL_IMAGES = "allImages";
    private static final String FILE_DIR = ConfigConstants.getFileDir();

    private final FileHandlerService fileHandlerService;
    private final OtherFilePreviewImpl otherFilePreview;

    public CadFilePreviewImpl(FileHandlerService fileHandlerService, OtherFilePreviewImpl otherFilePreview) {
        this.fileHandlerService = fileHandlerService;
        this.otherFilePreview = otherFilePreview;
    }
    @Value("${officedel:true}")
    private String officedel;
    @Override
    public String filePreviewHandle(String url, Model model, FileAttribute fileAttribute) {
        // 预览Type，参数传了就取参数的，没传取系统默认
        String gengxin=fileAttribute.getgengxin();
        String officePreviewType;
        String baseUrl = BaseUrlFilter.getBaseUrl();
        String fileName = fileAttribute.getName();
        String regEx = "[`#%:;.\"\\\\]";
        String fileNamee = Pattern.compile(regEx).matcher(fileName).replaceAll("").trim();
        String cadpdf = fileName.substring(fileName.lastIndexOf("."));
        String pdfName =  fileNamee + "." +  "pdf";
        String outFilePath = FILE_DIR + pdfName;
        boolean pdfgx ;
        if(StringUtil.isNotBlank(gengxin) && "ok".equalsIgnoreCase(gengxin)) { //去缓存更新
            pdfgx= false;
        }else {
            pdfgx= ConfigConstants.isCacheEnabled();
        }
        // 判断之前是否已转换过，如果转换过，直接返回，否则执行转换
        if (!pdfgx ||!fileHandlerService.listConvertedFiles().containsKey(pdfName) || !ConfigConstants.isCacheEnabled()) {
            String filePath;
            ReturnResponse<String> response = DownloadUtils.downLoad(fileAttribute, null);
            if (response.isFailure()) {
                return otherFilePreview.notSupportedFile(model, fileAttribute, response.getMsg());
            }
            filePath = response.getContent();
            if (StringUtils.hasText(outFilePath)) {
                boolean convertResult = fileHandlerService.cadToPdf(filePath, outFilePath);
                if( officedel.equalsIgnoreCase("false")){  //是否保留OFFICE源文件
                    KkFileUtils.deleteFileByPath(filePath);
                }
                if (!convertResult) {
                    return otherFilePreview.notSupportedFile(model, fileAttribute, "cad文件转换异常，请联系管理员");
                }
                if (ConfigConstants.isCacheEnabled()) {
                    // 加入缓存
                    fileHandlerService.addConvertedFile(pdfName, fileHandlerService.getRelativePath(outFilePath));
                }
            }
        }
        if(cadpdf.equalsIgnoreCase(".dwg") ||cadpdf.equalsIgnoreCase(".dxf") ){
            officePreviewType = "pdf";  //CAD格式强制使用PDF模式
        }else {
          officePreviewType = fileAttribute.getOfficePreviewType() == null ? ConfigConstants.getOfficePreviewType() : fileAttribute.getOfficePreviewType();
        }
        if (baseUrl != null && (OFFICE_PREVIEW_TYPE_IMAGE.equals(officePreviewType) || OFFICE_PREVIEW_TYPE_ALL_IMAGES.equals(officePreviewType))) {
            return getPreviewType(model, fileAttribute, officePreviewType, baseUrl, pdfName, outFilePath, fileHandlerService, OFFICE_PREVIEW_TYPE_IMAGE,otherFilePreview);
        }
        model.addAttribute("pdfUrl", pdfName);
        return PDF_FILE_PREVIEW_PAGE;
    }


}
