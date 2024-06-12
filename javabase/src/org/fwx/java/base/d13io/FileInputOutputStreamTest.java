package org.fwx.java.base.d13io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.time.Duration;
import java.time.LocalDateTime;

/**
 * @ClassName FileInputOutputStream
 * @Description TODO
 * @Author Fwx
 * @Date 2023/5/23 15:22
 * @Version 1.0
 */
public class FileInputOutputStreamTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileInputOutputStreamTest.class);

    public static void main(String[] args) {
        // 1.文件复制
        copyFile("D:\\5-study\\sgg-bigdata\\尚硅谷大数据学科全套教程（总185.88GB）\\1.尚硅谷大数据学科--核心基础\\尚硅谷Java核心基础\\30天全套视频\\day26_IO流\\01-尚硅谷-Java语言高级-每天一考.avi","javabase\\temp\\01-尚硅谷-Java语言高级-每天一考.avi");
    }

    /**
     * 文件拷贝
     * @param srcPath
     * @param destPath
     */
    public static void copyFile(String srcPath,String destPath){
        File file = new File(srcPath);
        if (!file.exists()) {
            LOGGER.info("****** app log, " + file.getAbsolutePath() + " is not exists.");
            return;
        }

        FileInputStream is = null;
        FileOutputStream os = null;
        long executionTime = 0;

        try {
            LocalDateTime startTime = LocalDateTime.now();
            is = new FileInputStream(file);
            os = new FileOutputStream(destPath);
            byte[] bytes = new byte[1024];
            int length;
            while ((length = is.read(bytes)) != -1){
                os.write(bytes,0, length);
            }
            LocalDateTime endTime = LocalDateTime.now();
            executionTime = Duration.between(startTime, endTime).toMillis();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            if (os != null){
                try {
                    os.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        LOGGER.info("****** app log, " + destPath + " copy success, execution time " + executionTime + "ms.");
    }
}
