import cn.hutool.crypto.digest.DigestUtil;
import java.util.*;
public class main {
    public static void main(String[] args) {
        String md5Hex = DigestUtil.md5Hex("123456");
        System.out.println("md5Hex = " + md5Hex);
    }
}
