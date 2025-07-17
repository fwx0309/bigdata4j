import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @ClassName SHA1
 * @Description TODO
 * @Author Fwx
 * @Date 2025/1/7 9:22
 * @Version 1.0
 */
public class SHA1 {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-1");
        byte[] digest1 = digest.digest("fwx".getBytes());
    }
}
