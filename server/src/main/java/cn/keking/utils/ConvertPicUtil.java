package cn.keking.utils;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.io.FileChannelRandomAccessSource;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.RandomAccessFileOrArray;
import com.itextpdf.text.pdf.codec.TiffImage;
import java.io.File;
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
    public static File convertJpg2Pdf(String strJpgFile, String strPdfFile) {
        try
        {
            RandomAccessFile aFile = new RandomAccessFile(strJpgFile, "r");
            FileChannel inChannel = aFile.getChannel();
            FileChannelRandomAccessSource fcra =  new FileChannelRandomAccessSource(inChannel);
            Document document = new Document();
            PdfWriter.getInstance(document,  new FileOutputStream(strPdfFile));
            document.open();
            RandomAccessFileOrArray rafa = new RandomAccessFileOrArray(fcra);
            int pages = TiffImage.getNumberOfPages(rafa);
            Image image;
            for (int i = 1; i <= pages; i++) {
                image = TiffImage.getTiffImage(rafa, i);
                Rectangle pageSize = new Rectangle(image.getWidth(), image.getHeight());
                document.setPageSize(pageSize);
                document.newPage();
                document.add(image);
            }
            document.close();
            aFile.close();
            File filePDF = new File(strPdfFile);
            return filePDF;
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
        return null;
    }
}

