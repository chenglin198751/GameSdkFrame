package com.game.sdk;

import android.webkit.URLUtil;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class UriUtils {

    public static String urlEncode(String uri) {

        // 进行Url编码
        uri = uri.replaceAll(" ", "%20");
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < uri.length(); i++) {
            char ch = uri.charAt(i);
            // MyLog.d("UriUtils", "" + uri.charAt(i));
            byte[] bytes = (String.valueOf(ch)).getBytes();

            if (bytes == null || bytes.length <= 0) { // 错误
                return "";
            } else if (bytes.length == 1) { // 英文字符
                sb.append(ch);
            } else {
                String tmp = new String(bytes);
                try {
                    tmp = URLEncoder.encode(tmp, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                sb.append(tmp);
            }
        }

        return sb.toString();
    }

    /**
     * @return True iff the url is a local file.
     */
    public static boolean isFileUrl(String url) {
        return URLUtil.isFileUrl(url) || new File(url).exists();
    }

}
