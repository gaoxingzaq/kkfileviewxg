package cn.keking.service;

import cn.keking.config.ConfigConstants;
import cn.keking.model.FileAttribute;
import cn.keking.model.FileType;
import cn.keking.service.cache.CacheService;
import cn.keking.utils.KkFileUtils;
import cn.keking.utils.UrlEncoderUtilss;
import cn.keking.utils.WebUtils;
import cn.keking.utils.WjtTypeUtils;
import com.aspose.cad.CodePages;
import com.aspose.cad.Color;
import com.aspose.cad.Image;
import com.aspose.cad.LoadOptions;
import com.aspose.cad.imageoptions.CadRasterizationOptions;
import com.aspose.cad.imageoptions.PdfOptions;
import com.itextpdf.text.pdf.PdfReader;
import jodd.util.StringUtil;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yudian-it
 */
@Component
public class FileHandlerService {

    private final Logger logger = LoggerFactory.getLogger(FileHandlerService.class);

    private static final String DEFAULT_CONVERTER_CHARSET = System.getProperty("sun.jnu.encoding");
    private final String fileDir = ConfigConstants.getFileDir();
    private final CacheService cacheService;
    @Value("${server.tomcat.uri-encoding:UTF-8}")
    private String uriEncoding;
    private static final String FILE_DIR = ConfigConstants.getFileDir();
    public FileHandlerService(CacheService cacheService) {
        this.cacheService = cacheService;
    }
    /**
     * @return ???????????????????????????(??????)
     */
    public Map<String, String> listConvertedFiles() {
        return cacheService.getPDFCache();
    }
    /**
     * @return ?????????????????????????????????????????????
     */
    public String getConvertedFile(String key) {
        return cacheService.getPDFCache(key);
    }
    /**
     * @param key pdf????????????
     * @return ??????pdf??????????????????????????????????????????
     */
    public Integer getConvertedPdfImage(String key) {
        return cacheService.getPdfImageCache(key);
    }
    /**
     * ???????????????????????????
     *
     * @param path ???????????????C:\Users\yudian-it\Downloads
     * @return ?????????
     */
    public String getFileNameFromPath(String path) {
        return path.substring(path.lastIndexOf(File.separator) + 1);
    }

    /**
     * ??????????????????
     *
     * @param absolutePath ????????????
     * @return ????????????
     */
    public String getRelativePath(String absolutePath) {
        return absolutePath.substring(fileDir.length());
    }
    /**
     * ??????????????????
     *
     */

    public static Map<String, Integer> AT_CONVERT_MAP =  new HashMap<>();

    /**
     * ???????????????PDF??????
     *
     * @param fileName pdf?????????
     * @param value    ??????????????????
     */
    public void addConvertedFile(String fileName, String value) {
        cacheService.putPDFCache(fileName, value);
    }

    /**
     * ??????????????????????????????
     *
     * @param pdfFilePath pdf??????????????????
     * @param num         ????????????
     */
    public void addConvertedPdfImage(String pdfFilePath, int num) {
        cacheService.putPdfImageCache(pdfFilePath, num);
    }

    /**
     * ??????redis???????????????????????????
     *
     * @param fileKey fileKey
     * @return ??????????????????url??????
     */
    public List<String> getImgCache(String fileKey) {
        return cacheService.getImgCache(fileKey);
    }

    /**
     * ??????redis???????????????????????????
     *
     * @param fileKey fileKey
     * @param imgs    ??????????????????url??????
     */
    public void putImgCache(String fileKey, List<String> imgs) {
        cacheService.putImgCache(fileKey, imgs);
    }

    /**
     * ?????????????????????????????????(??????????????????)
     *
     * @param outFilePath ??????????????????
     */
    public void doActionConvertedFile(String outFilePath) {
        StringBuilder sb = new StringBuilder();
        try (InputStream inputStream = new FileInputStream(outFilePath);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, DEFAULT_CONVERTER_CHARSET))) {
            String line;
            while (null != (line = reader.readLine())) {
                if (line.contains("charset=gb2312")) {
                    line = line.replace("charset=gb2312", "charset=utf-8");
                }
                sb.append(line);
            }
            // ??????sheet?????????
            sb.append("<script src=\"js/jquery-3.0.0.min.js\" type=\"text/javascript\"></script>");
            sb.append("<script src=\"js/excel.header.js\" type=\"text/javascript\"></script>");
            sb.append("<link rel=\"stylesheet\" href=\"bootstrap/css/xlsx.css\">");
        } catch (IOException e) {
            e.printStackTrace();
        }
        // ??????????????????
        try (FileOutputStream fos = new FileOutputStream(outFilePath);
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos, StandardCharsets.UTF_8))) {
            writer.write(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * ??????PDF ??????
     *
     */
    public static int pdfpage(String pdfName) {
        pdfName = pdfName.replace("%20", " ");
        File file = new File(FILE_DIR+pdfName);
        PdfReader pdfReader = null;
        try {
            pdfReader = new PdfReader(new FileInputStream(file)); //??????PDF?????? ??????????????????1??????????????????
        } catch (IOException e) {
            e.printStackTrace();
        }
        int pages = pdfReader.getNumberOfPages();
        return pages;
    }

    /**
     * ????????????PDF?????????????????????
     */
    private File getNetUrlHttp(String newUrl, String fileName){
        //????????????????????????
        String filePath=FILE_DIR+"ls"+fileName;
        File file = null;
        URL urlfile;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try{
            //????????????????????????????????????????????????????????????
            file = new File(filePath);
            if(!file.getParentFile().exists()){
                file.getParentFile().mkdirs();
            }
            try{
                //????????????
                file.createNewFile();
            }catch (Exception e){
                e.printStackTrace();
            }
            //??????
            urlfile = new URL(newUrl.replace(" ", "%20"));
            inputStream = urlfile.openStream();
            outputStream = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead=inputStream.read(buffer,0,8192))!=-1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (null != outputStream) {
                    outputStream.close();
                }
                if (null != inputStream) {
                    inputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    /**
     *  ??????????????????
     */
    public static double getDirSize(File file) {
        //????????????????????????
        if (file.exists()) {
            //???????????????????????????????????????????????????
            if (file.isDirectory()) {
                File[] children = file.listFiles();
                double size = 0;
                for (File f : children)
                    size += getDirSize(f);
                return size;
            } else {//???????????????????????????????????????,?????????????????????
                double size = (double) file.length() / 1024 / 1024;
                return size;
            }
        } else {
            System.out.println("???????????????????????????????????????????????????????????????");
            return 0.0;
        }
    }


    /**
     *  ?????????????????????
     */
    public static String zhuanyii(String fuhao) {
        fuhao = fuhao.replace("%","%25");
        //  fuhao = fuhao.replace("%","%25");
        //    fuhao = fuhao.replace("+","%2B");
        //   fuhao = fuhao.replace(" ","%20");
        //   fuhao = fuhao.replace("?","%3F");
        fuhao = fuhao.replace("[","%5B");
        fuhao = fuhao.replace("]","%5D");
        fuhao = fuhao.replace("#","%23");
        fuhao = fuhao.replace("&","%26");
        //    fuhao = fuhao.replace("=","%3D");
        return fuhao;
    }


    /**
     *  ??????????????????URL
     */
    public static boolean kuayu(String host, String wjl) {  //????????????????????????
        if (wjl.contains(host)) {
            return true;
        }else {
            return false;
        }
    }
    /**
     *  ??????URL??????
     */
    public static String hqurl(String url) {
        java.net.URL urls = null;
        try {
            urls = new java.net.URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        int port = urls.getPort();
        if (port < 0) {
            // ????????????????????????????????????
            port = urls.getDefaultPort();
        }
        String host = urls.getHost()+ ":" + port;// ??????http??????
        return host;
    }
    /**
     *  ???????????????
     */
    public static String geshi(String outFilePath,int ff) {
        String  geshi = null;
        try {
            if (ff ==1){  //OFD PDF ????????????
                geshi = WjtTypeUtils.pdftype(new FileInputStream(outFilePath));
            }else {
                geshi = WjtTypeUtils.getPicType(new FileInputStream(outFilePath));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return geshi;
    }
    /**
     *  pdf???????????????jpg?????????
     * pdfFilePath pdf????????????
     * pdfName pdf????????????
     * baseUrl ??????????????????
     * ??????????????????
     */

    @Value("${pdfpagee:0}")
    private String pdfpagee;
    public List<String> pdf2jpg(String pdfFilePath, String pdfName, String baseUrl, FileAttribute fileAttribute) {
        String gengxin=fileAttribute.getgengxin();
        List<String> imageUrls = new ArrayList<>();
        int pdfjpg= Integer.parseInt(ConfigConstants.getpdfjpg());
        Integer imageCount;
        String imageFileSuffix = ".jpg";
        String pdfFolder = pdfName.substring(0, pdfName.length() - 4);
        if(StringUtil.isNotBlank(gengxin) && "ok".equalsIgnoreCase(gengxin)) {  //???????????????
            imageCount = Integer.valueOf("0");
        }else {
            if(ConfigConstants.getpdffy().equalsIgnoreCase("false") ||pdfpagee.equalsIgnoreCase("0") ){    //????????????????????????
                imageCount = this.getConvertedPdfImage(pdfFilePath);
            }else {
                imageCount = this.getConvertedPdfImage(FILE_DIR+"ls"+pdfName);
            }
        }
        String urlPrefix;
        if(ConfigConstants.getpdffy().equalsIgnoreCase("false")){    //????????????????????????
            urlPrefix = baseUrl + pdfFolder;   //???????????????
        }else {
            if(pdfpagee.equalsIgnoreCase("0") || pdfpage(pdfName)<=1){
                urlPrefix = baseUrl + pdfFolder;   //???????????????
            }else {
                if (imageCount != null && imageCount > 0) {
                    urlPrefix = baseUrl +"ls"+ pdfFolder;  //???????????? ??????LS??????
                    pdfFilePath = FILE_DIR+"ls"+pdfName;
                }else {
                    urlPrefix = baseUrl +"ls"+ pdfFolder;  //???????????? ??????LS??????
                    pdfFilePath =baseUrl +"download?urlPath="+"file:///" + pdfFilePath+"?";    //?????????PDF????????????
                    pdfFilePath = String.valueOf(getNetUrlHttp(pdfFilePath,pdfName));
                }
            }
        }
        urlPrefix = urlPrefix.replaceFirst(baseUrl,"");
        String urlPrefixx= zhuanyii(urlPrefix);  //??????????????????
        if (imageCount != null && imageCount > 0) {
            for (int i = 0; i < imageCount; i++) {
                imageUrls.add(urlPrefixx + "/" + i + imageFileSuffix);
            }
            return imageUrls;
        }
        File pdfFile = new File(pdfFilePath);
        PDDocument doc = null;
        try {
            doc = PDDocument.load(pdfFile);
            int pageCount = doc.getNumberOfPages();
            PDFRenderer pdfRenderer = new PDFRenderer(doc);
            String folder = FILE_DIR + urlPrefix;
            File path = new File(folder);
            if (!path.exists() && !path.mkdirs()) {
                logger.error("?????????????????????{}??????????????????????????????????????????", folder);
            }
            String imageFilePath;
            for (int pageIndex = 0; pageIndex < pageCount; pageIndex++) {
                imageFilePath = folder + File.separator + pageIndex + imageFileSuffix;
                BufferedImage image = pdfRenderer.renderImageWithDPI(pageIndex, pdfjpg, ImageType.RGB);
                if(ConfigConstants.getyashuo().equalsIgnoreCase("true")) {
                    Thumbnails.of(image).scale(1.0).outputQuality(0.5).toFile(new File(imageFilePath));   //????????????
                }else {
                    ImageIOUtil.writeImage(image, imageFilePath, pdfjpg);
                }
                imageUrls.add(urlPrefixx + "/" + pageIndex + imageFileSuffix);
            }
            doc.close();
            this.addConvertedPdfImage(pdfFilePath, pageCount);
        } catch (IOException e) {
            logger.error("Convert pdf to jpg exception, pdfFilePath???{}", pdfFilePath, e);
        }finally{
            if(doc != null){   //??????
                try {
                    doc.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return imageUrls;
    }

    /**
     * cad?????????pdf
     * @param inputFilePath cad????????????
     * @param outputFilePath pdf??????????????????
     * @return ??????????????????
     */
    public boolean cadToPdf(String inputFilePath, String outputFilePath)  {
        File outputFile = new File(outputFilePath);
        FileHandlerService.AT_CONVERT_MAP.put(outputFile.getName(), 1);
        LoadOptions opts = new LoadOptions();
        opts.setSpecifiedEncoding(CodePages.SimpChinese);
        com.aspose.cad.Image cadImage = Image.load(inputFilePath, opts);
        CadRasterizationOptions cadRasterizationOptions = new CadRasterizationOptions();
        cadRasterizationOptions.setBackgroundColor(Color.getWhite());
        cadRasterizationOptions.setPageWidth(1400);
        cadRasterizationOptions.setPageHeight(650);
        cadRasterizationOptions.setAutomaticLayoutsScaling(true);
        cadRasterizationOptions.setNoScaling (false);
        cadRasterizationOptions.setDrawType(1);
        PdfOptions pdfOptions = new PdfOptions();
        pdfOptions.setVectorRasterizationOptions(cadRasterizationOptions);
        OutputStream stream;
        try {
            stream = new FileOutputStream(outputFile);
            cadImage.save(stream, pdfOptions);
            stream.close();
            cadImage.close();
            FileHandlerService.AT_CONVERT_MAP.remove(outputFile.getName(), 1);
            return true;
        } catch (IOException e) {
            logger.error("PDFFileNotFoundException???inputFilePath???{}", inputFilePath, e);
        }finally{
            if(cadImage != null){   //??????
                cadImage.close();
            }
        }
        return false;
    }

    /**
     * ??????????????????
     *
     * @param url url
     * @return ????????????
     */
    public FileAttribute getFileAttribute(String url, HttpServletRequest req) {
        FileAttribute attribute = new FileAttribute();
        String suffix;
        FileType type;
        String fileName;
        String fullFileName = WebUtils.getUrlParameterReg(url, "fullfilename");
        if (StringUtils.hasText(fullFileName)) {
            fileName = fullFileName;
            type = FileType.typeFromFileName(fullFileName);
            suffix = KkFileUtils.suffixFromFileName(fullFileName);
        } else {
            fileName = WebUtils.getFileNameFromURL(url);
            type = FileType.typeFromUrl(url);
            suffix = WebUtils.suffixFromUrl(url);
        }
        if(UrlEncoderUtilss.hasUrlEncoded(fileName) && UrlEncoderUtilss.hasUrlEncoded(suffix)){  //???????????????????????????
            try {
                fileName = URLDecoder.decode(fileName, "UTF-8");
                suffix = URLDecoder.decode(suffix, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        attribute.setType(type);
        attribute.setName(fileName);
        attribute.setSuffix(suffix);
        url = WebUtils.encodeUrlFileName(url);
        attribute.setUrl(url);
        if (req != null) {
            String officePreviewType = req.getParameter("officePreviewType");
            String fileKey = WebUtils.getUrlParameterReg(url,"fileKey");
            String gengxin = req.getParameter("gengxin");
            if (StringUtils.hasText(officePreviewType)) {
                attribute.setOfficePreviewType(officePreviewType);
            }
            if (StringUtils.hasText(fileKey)) {
                attribute.setFileKey(fileKey);
            }
            if (StringUtils.hasText(gengxin)) {
                attribute.setgengxin(gengxin);
            }
            String tifPreviewType = req.getParameter("tifPreviewType");
            if (StringUtils.hasText(tifPreviewType)) {
                attribute.setTifPreviewType(tifPreviewType);
            }
            String filePassword = req.getParameter("filePassword");
            if (StringUtils.hasText(filePassword)) {
                attribute.setFilePassword(filePassword);
            }
        }
        return attribute;
    }

    /**
     * @return ?????????????????????????????????(??????)
     */
    public Map<String, String> listConvertedMedias() {
        return cacheService.getMediaConvertCache();
    }

    /**
     * ????????????????????????????????????
     * fileName
     * value
     */
    public void addConvertedMedias(String fileName, String value) {
        cacheService.putMediaConvertCache(fileName, value);
    }

    /**
     * @return ???????????????????????????????????????????????????
     */
    public String getConvertedMedias(String key) {
        return cacheService.getMediaConvertCache(key);
    }
}
