package org.fwx.utils;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;

/**
 * md5 工具类
 */
public class MD5Utils {

    private static MD5Utils instance;

    private MD5Utils(){}

    /**
     * 获取实例
     * @return
     */
    public static MD5Utils getInstance(){
        if(instance == null){
            synchronized (MD5Utils.class){
                if (instance == null){
                    instance = new MD5Utils();
                }
            }
        }
        return instance;
    }

    /**
     * 计算字符串的 md5 值
     * @param data
     * @return md5 value
     */
    public String getStringMd5(String data){
        return DigestUtils.md5Hex(data);
    }

    /**
     * 计算文件的 md5 值
     * @param file
     * @return md5 value
     */
    public String getFileMd5(File file){
        FileInputStream fileInputStream = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            fileInputStream = new FileInputStream(file);
            byte[] buffer = new byte[8192];
            int length;
            while ((length = fileInputStream.read(buffer)) != -1){
                md5.update(buffer,0,length);
            }
            return new String(Hex.encodeHex(md5.digest()));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if(fileInputStream != null){
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        MD5Utils md5Utils = MD5Utils.getInstance();
        // 1. 测试获取字符串 md5
        String stringMd5 = md5Utils.getStringMd5("test");
        System.out.println(stringMd5);

        // 2. 测试获取文件 md5
        File file = new File("C:\\Windows\\System32\\drivers\\etc\\hosts");
        String fileMd5 = md5Utils.getFileMd5(file);
        System.out.println(fileMd5);

        File file1 = new File("C:\\Windows\\System32\\drivers\\etc\\hosts");
        String fileMd51 = md5Utils.getFileMd5(file1);
        System.out.println(fileMd51);

        File file2 = new File("C:\\Windows\\System32\\drivers\\etc\\hosts.ics");
        String fileMd52 = md5Utils.getFileMd5(file2);
        System.out.println(fileMd52);
    }

}
