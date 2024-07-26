package com.game.sdk;

import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.text.TextUtils;

public class MD5Util {

    private static final String TAG = "MD5Util";

    public static final String ALGORITHM = "MD5";

    public static final String DEFAULT_CHARSET = "UTF-8";

    public static String md5LowerCase(String string) {
        if (!TextUtils.isEmpty(string)) {
            try {
                byte[] buffer = string.getBytes(DEFAULT_CHARSET);
                if (buffer != null && buffer.length > 0) {
                    MessageDigest digester = MessageDigest.getInstance(ALGORITHM);
                    digester.update(buffer);
                    buffer = digester.digest();
                    string = toLowerCaseHex(buffer);
                }
            } catch (NoSuchAlgorithmException e) {
                SdkLogUtils.e(TAG, e.toString());
            } catch (UnsupportedEncodingException e) {
                SdkLogUtils.e(TAG, e.toString());
            } catch (Exception e) {
                SdkLogUtils.e(TAG, e.toString());
            }
        }

        return string;
    }

    public static String md5LowerCase2(String string) {
        return encode(string);
    }

    public static String encode(String string) {
        try {
            MessageDigest md = MessageDigest.getInstance(ALGORITHM);
            md.update(string.getBytes(DEFAULT_CHARSET));
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            return buf.toString();
            // if (type) {
            // return buf.toString(); // 32
            // } else {
            // return buf.toString().substring(8, 24);// 16
            // }
        } catch (Exception e) {
            return null;
        }
    }

    public static String md5(String string) {
        if (!TextUtils.isEmpty(string)) {
            try {
                byte[] buffer = string.getBytes(DEFAULT_CHARSET);
                if (buffer != null && buffer.length > 0) {
                    MessageDigest digester = MessageDigest.getInstance(ALGORITHM);
                    digester.update(buffer);
                    buffer = digester.digest();
                    string = new String(buffer);
                }
            } catch (NoSuchAlgorithmException e) {
                SdkLogUtils.e(TAG, e.toString());
            } catch (UnsupportedEncodingException e) {
                SdkLogUtils.e(TAG, e.toString());
            } catch (Exception e) {
                SdkLogUtils.e(TAG, e.toString());
            }
        }

        return string;
    }


    public static String md5LowerCase(byte[] buffer) {
        String ret = "";
        try {
            if (buffer != null && buffer.length > 0) {
                MessageDigest digester = MessageDigest.getInstance(ALGORITHM);
                digester.update(buffer);
                buffer = digester.digest();
                ret = toLowerCaseHex(buffer);
            }
        } catch (Throwable tr) {
            SdkLogUtils.e(TAG, "md5 error: ");
        }
        return ret;
    }

    private static String toLowerCaseHex(byte[] b) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < b.length; i++) {
            int v = b[i];
            builder.append(HEX_LOWER_CASE[(0xF0 & v) >> 4]);
            builder.append(HEX_LOWER_CASE[0x0F & v]);
        }
        return builder.toString();
    }

    private static final char[] HEX_LOWER_CASE = {
            '0', '1', '2', '3', '4', '5', '6', '7',
            '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
    };


    /**
     * 获取单个文件的MD5值！
     *
     * @param file
     * @return
     */

    public static String getFileMD5(File file) {
        if (!file.isFile()) {
            return null;
        }
        MessageDigest digest = null;
        FileInputStream in = null;
        String ret = "";
        byte buffer[] = new byte[1024];
        int len;
        try {
            digest = MessageDigest.getInstance("MD5");
            in = new FileInputStream(file);
            while ((len = in.read(buffer, 0, 1024)) != -1) {
                digest.update(buffer, 0, len);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
//     BigInteger bigInt = new BigInteger(1, digest.digest());
//     return bigInt.toString(16);
        ret = toLowerCaseHex(digest.digest());
        return ret;
    }

}
