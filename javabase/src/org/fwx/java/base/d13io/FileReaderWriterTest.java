package org.fwx.java.base.d13io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.time.Duration;
import java.time.LocalDateTime;

/**
 * @ClassName FileReaderTest
 * @Description TODO
 * @Author Fwx
 * @Date 2023/5/22 15:37
 * @Version 1.0
 */
public class FileReaderWriterTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileReaderWriterTest.class);

    public static void main(String[] args) {
        // 1.打印文件内容（一次读取单个字符输出）
//        print("javabase\\src\\org\\fwx\\java\\base\\d13io\\DeletePath.java");

        // 2.打印文件内容（一次读取多个字符输出）
//        printBatch("javabase\\src\\org\\fwx\\java\\base\\d13io\\DeletePath.java");

        // 3.文件复制
        copyFile("javabase\\src\\org\\fwx\\java\\base\\d13io\\DeletePath.java","javabase\\temp\\DeletePath.java");
    }

    /**
     * 打印文件内容（一次读取单个字符输出）
     * @param filePath
     */
    public static void print(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            LOGGER.info("****** app log, " + file.getAbsolutePath() + " is not exists.");
            return;
        }

        FileReader reader = null;
        try {
            reader = new FileReader(file);
            int data;
            while ((data = reader.read()) != -1){
                System.out.print((char)data);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    /**
     * 打印文件内容（一次读取多个字符输出）
     * @param filePath
     */
    public static void printBatch(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            LOGGER.info("****** app log, " + file.getAbsolutePath() + " is not exists.");
            return;
        }

        FileReader reader = null;
        try {
            reader = new FileReader(file);
            char[] chars = new char[1024];
            int data;
            while ((data = reader.read(chars)) != -1){
                System.out.print(new String(chars,0,data));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
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

        FileReader reader = null;
        FileWriter writer = null;
        long executionTime = 0;

        try {
            LocalDateTime startTime = LocalDateTime.now();
            reader = new FileReader(file);
            writer = new FileWriter(destPath);
            char[] chars = new char[1024];
            int length;
            while ((length = reader.read(chars)) != -1){
                writer.write(new String(chars,0, length));
            }
            LocalDateTime endTime = LocalDateTime.now();
            executionTime = Duration.between(startTime, endTime).toMillis();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            if (writer != null){
                try {
                    writer.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        LOGGER.info("****** app log, " + destPath + " copy success, execution time " + executionTime + "ms.");
    }
}
