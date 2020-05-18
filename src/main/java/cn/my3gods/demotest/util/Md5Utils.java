package cn.my3gods.demotest.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * <br></br>
 *
 * @author Charlie
 * @version 1.0
 * @date 2020/5/18 15:41
 */
public class Md5Utils {

    public static final String MD5 = "MD5";

    public static final String UTF8 = "UTF-8";

    private Md5Utils() {
    }

    public static String md5For16(String param) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        byte[] digest = getMd5Digest(param);
        StringBuilder builder = new StringBuilder();
        // 16位MD5为 截取32位MD5的4-11位
        for (int i = 4; i < 12; i++) {
            // 10进制转16进制，X 表示以十六进制形式输出，02 表示不足两位前面补0输出
            builder.append(String.format("%02X", digest[i]));
        }
        return builder.toString();
    }

    public static String md5For32(String param) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        byte[] digest = getMd5Digest(param);
        StringBuilder builder = new StringBuilder();
        for (byte b : digest) {
            // 10进制转16进制，X 表示以十六进制形式输出，02 表示不足两位前面补0输出
            builder.append(String.format("%02X", b));
        }
        return builder.toString();
    }


    public static String md5For16(String param, String salt) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        byte[] digest = getMd5Digest(param, salt);
        StringBuilder builder = new StringBuilder();
        // 16位MD5为 截取32位MD5的4-11位
        for (int i = 4; i < 12; i++) {
            // 10进制转16进制，X 表示以十六进制形式输出，02 表示不足两位前面补0输出
            builder.append(String.format("%02X", digest[i]));
        }
        return builder.toString();
    }

    public static String md5For32(String param, String salt) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        byte[] digest = getMd5Digest(param, salt);
        StringBuilder builder = new StringBuilder();
        for (byte b : digest) {
            // 10进制转16进制，X 表示以十六进制形式输出，02 表示不足两位前面补0输出
            builder.append(String.format("%02X", b));
        }
        return builder.toString();
    }

    public static byte[] getMd5Digest(String param) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md5 = MessageDigest.getInstance(MD5);
        md5.reset();
        // 获得密文完成哈希计算,产生128 位的长整数
        md5.update(param.getBytes(UTF8));
        // 16位
        return md5.digest();
    }

    public static byte[] getMd5Digest(String param, String salt) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md5 = MessageDigest.getInstance(MD5);
        md5.reset();
        // 获得密文完成哈希计算,产生128 位的长整数
        md5.update((salt + param).getBytes(UTF8));
        // 16位
        return md5.digest();
    }

}
