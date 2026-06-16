package com.rag.common.util;

import cn.hutool.crypto.SecureUtil;

public class Md5Util {

    private static final String secrete = "yxl123456789";

    public static String md5(String data){
        return SecureUtil.md5(data+secrete);
    }

}
