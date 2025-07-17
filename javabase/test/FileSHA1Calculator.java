import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FileSHA1Calculator {

    public static String calculateFileSHA1(File file) throws NoSuchAlgorithmException, IOException {
        // 获取SHA1算法的MessageDigest实例
        MessageDigest md = MessageDigest.getInstance("SHA-1");

        try (FileInputStream fis = new FileInputStream(file)) {
            byte[] buffer = new byte[8192];  // 定义每次读取文件的缓冲区大小，可以根据实际情况调整
            int bytesRead;
            // 循环读取文件内容，直到读取完毕
            while ((bytesRead = fis.read(buffer))!= -1) {
                // 使用读取到的字节数据更新MessageDigest
                md.update(buffer, 0, bytesRead);
            }
        }

        // 计算最终的哈希值，得到字节数组形式的结果
        byte[] digest = md.digest();

        StringBuilder hexString = new StringBuilder();
        // 遍历字节数组，将每个字节转换为十六进制字符串并拼接起来
        for (byte b : digest) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }

        return hexString.toString();
    }

    public static void main(String[] args) {
        File file = new File("C:\\your\\file\\path\\example.txt");  // 这里替换成实际要计算哈希值的文件路径
        try {
            String sha1Hash = calculateFileSHA1(file);
            System.out.println("文件 " + file.getName() + " 的SHA1哈希值为: " + sha1Hash);
        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
        }
    }
}