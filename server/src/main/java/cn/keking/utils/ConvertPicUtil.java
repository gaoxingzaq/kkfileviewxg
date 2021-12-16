package cn.keking.utils;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.io.FileChannelRandomAccessSource;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.RandomAccessFileOrArray;
import com.itextpdf.text.pdf.codec.TiffImage;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

public class ConvertPicUtil {
    /**
     * 将Jpg图片转换为Pdf文件
     * @param strJpgFile 输入的jpg的路径和文件名
     * @param strPdfFile 输出的pdf的路径和文件名
     * @return
     */
    private static final int FIT_WIDTH = 500;
    private static final int FIT_HEIGHT = 900;
    public static boolean convertJpg2Pdf(String strJpgFile, String strPdfFile) {
        try
        {
            Document document = new Document();
            PdfWriter.getInstance(document,  new FileOutputStream(strPdfFile));
            document.open();
            RandomAccessFile aFile = new RandomAccessFile(strJpgFile, "r");
            FileChannel inChannel = aFile.getChannel();
            FileChannelRandomAccessSource fcra =  new FileChannelRandomAccessSource(inChannel);
            RandomAccessFileOrArray rafa = new RandomAccessFileOrArray(fcra);
            int pages = TiffImage.getNumberOfPages(rafa);
            Image image;
            for (int i = 1; i <= pages; i++) {
                try {
                    image = TiffImage.getTiffImage(rafa, i);
                    image.scaleToFit(FIT_WIDTH, FIT_HEIGHT);
                    document.add(image);
                } catch (Exception e) {
                    document.close();
                    aFile.close();
                    e.printStackTrace();
                    return false;
                }
            }
            document.close();
            aFile.close();
            return true;
        }
        catch (Exception e)
        {
            KkFileUtils.deleteFileByPath(strPdfFile);
           System.out.println("错误:"+ e.getMessage());
        }
        return false;
    }
}

