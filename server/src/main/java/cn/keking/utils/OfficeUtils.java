package cn.keking.utils;

import org.apache.poi.poifs.filesystem.OfficeXmlFileException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * Office工具类
 *
 * @author ylyue
 * @since 2022/7/5
 */
public class OfficeUtils {

    /**
     * 判断office文件是否加密
     *
     * @param path office文件路径
     * @return 是否加密
     */
    public static boolean isEncrypted(String path) {
        try {
            try {
                new POIFSFileSystem(new FileInputStream(path));
            } catch (IOException ex) {

            }
            return true;
        } catch (OfficeXmlFileException e) {
            return false;
        }
    }

}
