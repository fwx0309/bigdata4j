package org.fwx.java.base.d13io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.time.Duration;
import java.time.LocalDateTime;

/**
 * @ClassName BufferedTest
 * @Description TODO
 * @Author Fwx
 * @Date 2023/5/23 17:42
 * @Version 1.0
 */
public class BufferedTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(BufferedTest.class);

    public static void main(String[] args) {
        // 1.文件复制
//        copyFileOnBytes("D:\\5-study\\sgg-bigdata\\尚硅谷大数据学科全套教程（总185.88GB）\\1.尚硅谷大数据学科--核心基础\\尚硅谷Java核心基础\\30天全套视频\\day26_IO流\\01-尚硅谷-Java语言高级-每天一考.avi","javabase\\temp\\01-尚硅谷-Java语言高级-每天一考.avi");

        // 2.文本文件复制
        copyFileOnChars("javabase\\src\\org\\fwx\\java\\base\\d13io\\DeletePath.java","javabase\\temp\\DeletePath.java");
    }

    /**
     * 文件拷贝（字节流）
     * @param srcPath
     * @param destPath
     */
    public static void copyFileOnBytes(String srcPath, String destPath){
        File file = new File(srcPath);
        if (!file.exists()) {
            LOGGER.info("****** app log, " + file.getAbsolutePath() + " is not exists.");
            return;
        }

        FileInputStream is = null;
        FileOutputStream os = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        long executionTime = 0;

        try {
            LocalDateTime startTime = LocalDateTime.now();

            is = new FileInputStream(file);
            os = new FileOutputStream(destPath);
            // 缓冲流
            bis = new BufferedInputStream(is);
            bos = new BufferedOutputStream(os);

            byte[] bytes = new byte[1024];
            int length;
            while ((length = bis.read(bytes)) != -1){
                bos.write(bytes,0, length);
            }
            bos.flush();
            LocalDateTime endTime = LocalDateTime.now();
            executionTime = Duration.between(startTime, endTime).toMillis();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            // 关闭缓冲流即可
            if (bis != null){
                try {
                    bis.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            if (bos != null){
                try {
                    bos.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        LOGGER.info("****** app log, " + destPath + " copy success, execution time " + executionTime + "ms.");
    }

    /**
     * 文件拷贝（字节流）
     * @param srcPath
     * @param destPath
     */
    public static void copyFileOnChars(String srcPath,String destPath){
        File file = new File(srcPath);
        if (!file.exists()) {
            LOGGER.info("****** app log, " + file.getAbsolutePath() + " is not exists.");
            return;
        }

        FileReader reader = null;
        FileWriter writer = null;
        BufferedReader breader = null;
        BufferedWriter bwriter = null;
        long executionTime = 0;

        try {
            LocalDateTime startTime = LocalDateTime.now();

            reader = new FileReader(file);
            writer = new FileWriter(destPath);
            // 缓冲流
            breader = new BufferedReader(reader);
            bwriter = new BufferedWriter(writer);

            // 方式一
            /*char[] chars = new char[1024];
            int length;
            while ((length = breader.read(chars)) != -1){
                bwriter.write(chars,0, length);
            }*/
            // 方式二
            String line = null;
            while ((line = breader.readLine()) != null){
                bwriter.write(line);
                bwriter.newLine();
            }

            bwriter.flush();
            LocalDateTime endTime = LocalDateTime.now();
            executionTime = Duration.between(startTime, endTime).toMillis();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            // 关闭缓冲流即可
            if (breader != null){
                try {
                    breader.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            if (bwriter != null){
                try {
                    bwriter.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        LOGGER.info("****** app log, " + destPath + " copy success, execution time " + executionTime + "ms.");
    }
}
