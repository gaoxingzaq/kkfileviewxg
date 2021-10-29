package cn.keking.service;


import cn.keking.config.ConfigConstants;
import org.apache.poi.hslf.usermodel.*;
import org.apache.poi.xslf.usermodel.*;
import org.springframework.beans.factory.annotation.Value;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Base64;
/**
 * ppt转html——poi
 *
 * @author DHJT 2018年5月1日-下午12:30:59
 */
public class POIPptToHtml {

    private final static String PPT = "ppt";
    private final static String PPTX = "pptx";
    private static final String FILE_DIR = ConfigConstants.getFileDir();
    public static boolean pptToHtml(String sourcePath,  String fileName, String targetDir, int pptx) {
        File pptFile = new File(sourcePath);
        if (pptFile.exists()) {
            try {
                String type = FileUtils.GetFileExt(sourcePath);
                if (PPT.equals(type)) {
                    String htmlStr = toImage2003(sourcePath, targetDir, fileName, pptx);
                    FileUtils.writeFile(htmlStr, targetDir);
                    return true;
                } else if (PPTX.equals(type)) {
                    String htmlStr = toImage2007(sourcePath, targetDir, fileName, pptx);
                    FileUtils.writeFile(htmlStr, targetDir);
                    return true;
                } else {
                    System.out.println("the file is not a ppt");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("file does not exist!");
        }
        return false;
    }

    public static String toImage2007(String sourcePath, String targetDir, String pptFileName, int pptx) throws Exception {
        String htmlStr = "";
        final int times = pptx;  //放大图片倍数
        FileInputStream is = new FileInputStream(sourcePath);
        XMLSlideShow ppt = new XMLSlideShow(is);
        is.close();
        Dimension pgsize = ppt.getPageSize();
        System.out.println(pgsize.width + "--" + pgsize.height);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < ppt.getSlides().size(); i++) {
            try {
                // 防止中文乱码
                for (XSLFShape shape : ppt.getSlides().get(i).getShapes()) {
                    if (shape instanceof XSLFTextShape) {
                        XSLFTextShape tsh = (XSLFTextShape) shape;
                        for (XSLFTextParagraph p : tsh) {
                            for (XSLFTextRun r : p) {
                                r.setFontFamily("宋体");
                            }
                        }
                    }
                }
                BufferedImage img = new BufferedImage(pgsize.width*times, pgsize.height*times, BufferedImage.TYPE_INT_RGB);  //放大图片
                Graphics2D graphics = img.createGraphics();
                // clear the drawing area
                graphics.setPaint(Color.white);
                graphics.scale(times,times);
                graphics.fill(new Rectangle2D.Float(0, 0, pgsize.width*times, pgsize.height*times));   //放大图片
                // render
                ppt.getSlides().get(i).draw(graphics);
                // save the output
                String imageDir = FILE_DIR + "/" + pptFileName + "/";
                FileUtils.createDir(imageDir);// create image dir
                String imagePath = imageDir + pptFileName + "-" + (i + 1) + ".png";
                sb.append("<br>");
                String imagePathh = imagePath.replace(FILE_DIR, "");
                sb.append("<img src=" + "\"" + imagePathh + "\"" + "/>");
                FileOutputStream out = new FileOutputStream(imagePath);
                javax.imageio.ImageIO.write(img, "png", out);
                out.close();
            } catch (Exception e) {
                System.out.println("第" + i + "张ppt转换出错");
            }
        }
        System.out.println("转换成功");
        htmlStr = sb.toString();
        return htmlStr;
    }

    public static String toImage2003(String sourcePath, String targetDir, String pptFileName, int pptx) {
        String htmlStr = "";
        final int times = pptx;  //放大图片倍数
        try {
            HSLFSlideShow ppt = new HSLFSlideShow(new HSLFSlideShowImpl(sourcePath));
            FileUtils.createDir(targetDir);// create html dir
            Dimension pgsize = ppt.getPageSize();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < ppt.getSlides().size(); i++) {
                // 防止中文乱码
                for (HSLFShape shape : ppt.getSlides().get(i).getShapes()) {
                    if (shape instanceof HSLFTextShape) {
                        HSLFTextShape tsh = (HSLFTextShape) shape;
                        for (HSLFTextParagraph p : tsh) {
                            for (HSLFTextRun r : p) {
                                r.setFontFamily("宋体");
                            }
                        }
                    }
                }
                BufferedImage img = new BufferedImage(pgsize.width*times, pgsize.height*times, BufferedImage.TYPE_INT_RGB);
                Graphics2D graphics = img.createGraphics();
                // clear the drawing area
                graphics.setPaint(Color.white);
                graphics.scale(times,times);
                graphics.fill(new Rectangle2D.Float(0, 0, pgsize.width*times, pgsize.height*times));
                // render
                ppt.getSlides().get(i).draw(graphics);
                String imageDir = targetDir + "/" + pptFileName + "/";
                FileUtils.createDir(imageDir);// create image dir
                String imagePath = imageDir + pptFileName + "-" + (i + 1) + ".png";
                sb.append("<br>");
                String imagePathh = imagePath.replace(FILE_DIR, "");
                sb.append("<img src=" + "\"" + imagePathh + "\"" + "/>");
                FileOutputStream out = new FileOutputStream(imagePath);
                javax.imageio.ImageIO.write(img, "png", out);
                out.close();
            }
            htmlStr = sb.toString();
        } catch (Exception e) {

        }
        return htmlStr;
    }
    public static void resizeImage(String srcImgPath, String distImgPath, int width, int height) throws IOException {
        File srcFile = new File(srcImgPath);
        Image srcImg = ImageIO.read(srcFile);
        BufferedImage buffImg = null;
        buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        buffImg.getGraphics().drawImage(srcImg.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);
        ImageIO.write(buffImg, "JPEG", new File(distImgPath));
    }
}

