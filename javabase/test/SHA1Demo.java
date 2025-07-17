import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA1Demo {
    public static void main(String[] args) {
        String input = "要进行哈希计算的内容";  // 这里可以替换成你实际想要计算哈希的字符串
        try {
            // 获取SHA1算法的MessageDigest实例
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            // 将输入字符串转换为字节数组并更新到MessageDigest中
            md.update(input.getBytes());
            // 计算哈希值，得到字节数组形式的结果
            byte[] digest = md.digest();
            // 将字节数组转换为十六进制表示的字符串，方便查看和使用
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < digest.length; i++) {
                String hex = Integer.toHexString(0xff & digest[i]);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            System.out.println("SHA1哈希值: " + hexString.toString());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}