package com.imooc.o2o.util;

import javax.servlet.http.HttpServletRequest;

public class HttpServletRequestUtil {
    /**
     * 从request中获取int类型数据
     * @param request
     * @param key
     * @return
     */
    public static int getInt(HttpServletRequest request,String key){
        try {
            return Integer.decode(request.getParameter(key));
        }catch (Exception e){
            return -1;
        }
    }

    /**
     * 从request中获取Long数据
     * @param request
     * @param key
     * @return
     */
    public static long getLong(HttpServletRequest request,String key){
        try {
            return Long.valueOf(request.getParameter(key));
        }catch (Exception e){
            return -1L;
        }
    }

    /**
     * 从request中获取double数据
     * @param request
     * @param key
     * @return
     */
    public static double getDouble(HttpServletRequest request,String key){
        try {
            return Double.valueOf(request.getParameter(key));
        }catch (Exception e){
            return -1d;
        }
    }

    /**
     * 从request中获取boolean数据
     * @param request
     * @param key
     * @return
     */
    public static boolean getBoolean(HttpServletRequest request,String key){
        try {
            return Boolean.valueOf(request.getParameter(key));
        }catch (Exception e){
            return false;
        }
    }

    /**
     * 从request中获取String数据
     * @param request
     * @param key
     * @return
     */
    public static String getString(HttpServletRequest request,String key){
        try {
            String result=request.getParameter(key);
            if (result!=null){
                result = result.trim();
            }
            if ("".equals(result)) {
                result=null;
            }
            return result;
        }catch (Exception e){
            return null;
        }
    }
}
