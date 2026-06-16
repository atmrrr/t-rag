package com.rag.common.util;

import cn.hutool.json.JSON;
import com.rag.common.dto.LoginUserInfo;

public class UserContext  {

    private static final ThreadLocal<LoginUserInfo> LOGIN_USER_CONTEXT = new ThreadLocal<>();

    /**
     * 保存用户信息
     */
    public static void setUserInfo(LoginUserInfo loginUserInfo){
        LOGIN_USER_CONTEXT.set(loginUserInfo);

    }

    /**
     * 获取用户信息
     */
    public static LoginUserInfo getLoginUser(){
        return LOGIN_USER_CONTEXT.get();
    }


    /**
     * 清理
     */

    public static void remove(){
        LOGIN_USER_CONTEXT.remove();
    }


}
