package com.ljheee.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 *
 *  将URL中 %BD%BF%E7%94%A8%E7%AC%94%E8%AE 的字符解码
 */
public class UrlDecode {

    public static void main(String[] args) throws UnsupportedEncodingException {
        String url = "https://www.mspring.org/2018/08/14/javacv%E4%BD%BF%E7%94%A8%E7%AC%94%E8%AE%B0/";
        String decodeStr = URLDecoder.decode(url, "utf-8");
        System.out.println(decodeStr); // https://www.mspring.org/2018/08/14/javacv使用笔记/

    }
}
