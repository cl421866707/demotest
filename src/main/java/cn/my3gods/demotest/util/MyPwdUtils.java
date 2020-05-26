package cn.my3gods.demotest.util;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * <br></br>
 *
 * @author Charlie
 * @version 1.0
 * @date 2020/5/18 15:58
 */
public class MyPwdUtils {

    public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        if (args.length == 0) {
            return;
        }
        String pwd = args[0];
        for (int i = 0; i < 3; i++) {
            pwd = Md5Utils.md5For16(pwd, "cl");
            System.err.println("index:" + (i + 1) + ";result:" + pwd + "\n");
        }
    }
}
