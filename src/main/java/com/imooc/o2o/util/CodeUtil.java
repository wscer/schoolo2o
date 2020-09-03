package com.imooc.o2o.util;

import com.google.code.kaptcha.Constants;

import javax.servlet.http.HttpServletRequest;

public class CodeUtil {
    /**
     * 进行验证码校验的方法
     * @param request
     * @return
     */
    public static boolean checkVerifyCode(HttpServletRequest request){
        String verifyCodeExpected=(String)request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        String verifyCodeActual= HttpServletRequestUtil.getString(request,"verifyCodeActual");
        if (verifyCodeActual==null||!verifyCodeActual.equals(verifyCodeExpected)){
            return false;
        }else {
            return true;
        }
    }
}
