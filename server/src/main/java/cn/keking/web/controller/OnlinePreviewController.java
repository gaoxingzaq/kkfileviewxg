package cn.keking.web.controller;

import cn.keking.config.ConfigConstants;
import cn.keking.model.FileAttribute;
import cn.keking.service.FileHandlerService;
import cn.keking.service.FilePreview;
import cn.keking.service.FilePreviewFactory;
import cn.keking.service.cache.CacheService;
import cn.keking.service.impl.OtherFilePreviewImpl;
import cn.keking.utils.WebUtils;
import cn.keking.web.filter.BaseUrlFilter;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import fr.opensagres.xdocreport.core.io.IOUtils;
import io.mola.galimatias.GalimatiasParseException;
import jodd.io.NetUtil;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import static cn.keking.service.FilePreview.Jiaz_FILE_PAGE;
import static cn.keking.service.FilePreview.PICTURE_FILE_PREVIEW_PAGE;

/**
 * @author yudian-it
 */
@Controller
public class OnlinePreviewController {

    public static final String BASE64_DECODE_ERROR_MSG = "Base64解码失败，请检查你的 %s 是否采用 Base64 + urlEncode 双重编码了！";
    private final Logger logger = LoggerFactory.getLogger(OnlinePreviewController.class);
    private static final String FILE_DIR = ConfigConstants.getFileDir();
    private final FilePreviewFactory previewFactory;
    private final CacheService cacheService;
    private final FileHandlerService fileHandlerService;
    private final OtherFilePreviewImpl otherFilePreview;

    public OnlinePreviewController(FilePreviewFactory filePreviewFactory, FileHandlerService fileHandlerService, CacheService cacheService, OtherFilePreviewImpl otherFilePreview) {
        this.previewFactory = filePreviewFactory;
        this.fileHandlerService = fileHandlerService;
        this.cacheService = cacheService;
        this.otherFilePreview = otherFilePreview;
    }
    @Value("${pdfpagee:0}")
    private String pdfpagee;
    @RequestMapping(value = "/onlinePrevieww")
    public String onlinePrevieww(String url, Model model, HttpServletRequest req) {
        String ip = req.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = req.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = req.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = req.getRemoteAddr();
        }
        if (ip.contains(",")) {
            ip = ip.split(",")[0];
        }
        String fileUrl;
        try {
            if(IndexController.isBase64(url)){
                fileUrl = new String(Base64.decodeBase64(url), StandardCharsets.UTF_8);
            }else {
                fileUrl = url;
            }
        } catch (Exception ex) {
            String errorMsg = String.format(BASE64_DECODE_ERROR_MSG, "url");
            return otherFilePreview.notSupportedFile(model, errorMsg);
        }
        FileAttribute fileAttribute = fileHandlerService.getFileAttribute(fileUrl, req);
        model.addAttribute("file", fileAttribute);
        FilePreview filePreview = previewFactory.get(fileAttribute);
        if(!ConfigConstants.getlocalpreview().equalsIgnoreCase("false")) {
            if (fileUrl == null || fileUrl.toLowerCase().startsWith("file:") || fileUrl.toLowerCase().startsWith("file%3")) {
                logger.info("URL异常", fileUrl);
                return otherFilePreview.notSupportedFile(model, "该文件不允许预览：" + fileUrl);
            }
        }
        boolean wjl = FileHandlerService.kuayu("&fullfilename=", fileUrl);  //判断是否启用文件流
        if(wjl){
            fileUrl =  fileUrl.substring(0,fileUrl.lastIndexOf("&"));  //删除添加的文件流内容
        }
        logger.info("预览文件url：{}，IP：{}，previewType：{}", fileUrl,ip, fileAttribute.getType());
        return filePreview.filePreviewHandle(fileUrl, model, fileAttribute);
    }
    @RequestMapping(value = "/onlinePreview")
    public String onlinePreview(HttpServletRequest request, Model model) throws IOException{
        String query = request.getQueryString();
        if(query == null){
            return otherFilePreview.notSupportedFile(model, "url异常或者不正确：" + query);
        }
        String urlPath = query.replaceFirst("url=","");
        model.addAttribute("pdfUrl",urlPath);
        return Jiaz_FILE_PAGE;
    }

    @RequestMapping(value = "/picturesPreview")
    public String picturesPreview(String urls, Model model, HttpServletRequest req) throws UnsupportedEncodingException {
        String fileUrls;
        try {
            if(IndexController.isBase64(urls)){
                fileUrls = new String(Base64.decodeBase64(urls));
            }else {
                fileUrls = urls;
            }

        } catch (Exception ex) {
            String errorMsg = String.format(BASE64_DECODE_ERROR_MSG, "urls");
            return otherFilePreview.notSupportedFile(model, errorMsg);
        }
        if(!ConfigConstants.getlocalpreview().equalsIgnoreCase("false")) {
            if (fileUrls == null || fileUrls.toLowerCase().startsWith("file:") || fileUrls.toLowerCase().startsWith("file%3")) {
                logger.info("URL异常", fileUrls);
                return otherFilePreview.notSupportedFile(model, "该文件不允许预览：" + fileUrls);
            }
        }
        boolean wjl = FileHandlerService.kuayu("&fullfilename=", fileUrls.toLowerCase());  // 转换成小写 判断是否启用文件流
        if(wjl){
            fileUrls =  fileUrls.substring(0,fileUrls.lastIndexOf("&"));  //删除添加的文件流内容
        }
        logger.info("预览文件url：{}，urls：{}", fileUrls, urls);
        // 抽取文件并返回文件列表
        String[] images = fileUrls.split("\\|");
        List<String> imgUrls = Arrays.asList(images);
        model.addAttribute("imgUrls", imgUrls);

        String currentUrl = req.getParameter("currentUrl");
        if (StringUtils.hasText(currentUrl)) {
            String decodedCurrentUrl = new String(Base64.decodeBase64(currentUrl));
            model.addAttribute("currentUrl", decodedCurrentUrl);
        } else {
            model.addAttribute("currentUrl", imgUrls.get(0));
        }
        return PICTURE_FILE_PREVIEW_PAGE;
    }

    /**
     * 根据url获取文件内容
     * 当pdfjs读取存在跨域问题的文件时将通过此接口读取
     *
     * @param response response
     */
    @RequestMapping(value = "/getCorsFile", method = RequestMethod.GET)
    public void getCorsFile( HttpServletRequest request, Model model, HttpServletResponse response) {
        String query = request.getQueryString();
               query = query.replace("%20", " ");
        try {
            query = URLDecoder.decode(query, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String urlPath = query.replaceFirst("urlPath=","");
               urlPath = urlPath.replaceFirst("&disabledownload=true","");
        if (urlPath == null || urlPath.toLowerCase().startsWith("file:") || urlPath.toLowerCase().startsWith("file%3") || !urlPath.toLowerCase().startsWith("http")) {
            logger.info("读取跨域文件异常", urlPath);
        }else {
            logger.info("读取跨域文件url：{}", urlPath);
            try {
                URL url = WebUtils.normalizedURL(urlPath);
                byte[] bytes = NetUtil.downloadBytes(url.toString());
                IOUtils.write(bytes, response.getOutputStream());
            } catch (IOException | GalimatiasParseException e) {
                logger.error("读取跨域文件异常，url：{}", urlPath, e);
            }
        }
    }
    /**
     * PDF分片功能
     */
    @RequestMapping( value = "/download", method = RequestMethod.GET)
    public void pdf(HttpServletRequest request, HttpServletResponse response) throws IOException, DocumentException {
        //文件路径
        String query = request.getQueryString();
        String urlPath = query.replaceFirst("urlPath=","");
        String page = null;
        if(pdfpagee.equalsIgnoreCase("0")){
            page = urlPath.substring(urlPath.lastIndexOf("=")+1);
            urlPath = urlPath.substring(0,urlPath.lastIndexOf("?"));
        }else {
            urlPath = urlPath.substring(0,urlPath.lastIndexOf("?"));
        }
      if (page == null || "".equals(page) ||"pdf".equals(page) ||"NaN".equals(page) ){
          page="1";
      }
        logger.info("读取PDF分页文件url：{}", URLDecoder.decode(urlPath, "UTF-8"));
        String pdfname = urlPath.substring(urlPath.lastIndexOf("."));
        if(pdfname.equalsIgnoreCase(".pdf")){ //判断是否PDF文件
        // 读取pdf文档
       if(urlPath.toLowerCase().startsWith("file:") || urlPath.toLowerCase().startsWith("file%3")){
            logger.info("文件地址异常", urlPath);
        }else if(!urlPath.toLowerCase().startsWith("http")){
           urlPath ="file:///"+  FILE_DIR + urlPath;
       }
     // System.out.println(urlPath);
        PdfReader reader = new PdfReader(urlPath);
        //总页数
        int numberOfPages = reader.getNumberOfPages();
        // 截取开始页
            //截取pdf部分页，格式"2-5" 第2页到第5页 页码超出范围（10页，你选择"15-20"）只会读最后一页
            // 参数为String型，可让前端传值，控制读取第几页
            // reader.selectPages("2-5");
            int start;
            if(pdfpagee.equalsIgnoreCase("0")){
                start = Integer.parseInt(page.substring(0, 1));
                reader.selectPages(page);
            }else {
                start = Integer.parseInt(pdfpagee.substring(0, 1));
                reader.selectPages(pdfpagee);
            }
        //源码没怎么看懂，但是需要内存中存放文件流，所以用了HttpServletResponse
        PdfStamper stamp = new PdfStamper(reader, response.getOutputStream());
        // 开始页 如果大于pdf总页数，不返回文件流，stamp.close()结果返回1
        if(start <= numberOfPages){
            stamp.close();
        }
        reader.close();
        }else {
            logger.info("文件异常", urlPath);
        }
    }
    /**
     * 通过api接口入队
     *
     * @param url 请编码后在入队
     */
    @RequestMapping("/addTask")
    @ResponseBody
    public String addQueueTask(String url) {
        logger.info("添加转码队列url：{}", url);
        cacheService.addQueueTask(url);
        return "success";
    }
}
