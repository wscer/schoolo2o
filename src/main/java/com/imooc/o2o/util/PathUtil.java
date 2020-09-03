package com.imooc.o2o.util;

public class PathUtil {
    private static String separator=System.getProperty("file.separator");

    /**
     * 获取图片保存根路径
     * @return
     */
    public static String getImgBasePath(){
        String os=System.getProperty("os.name");
        String bastPath="";
        if (os.toLowerCase().startsWith("win")){
            bastPath="E:/o2o/image";
        }else {
            bastPath="/Users/baidu/work/image";
        }
        bastPath=bastPath.replace("/",separator);
        return bastPath;
    }

    /**
     * 获取各个店铺自己存放图片的相对路径
     * @param shopId 店铺Id
     * @return
     */
    public static String getShopImagePath(long shopId){
        String imagePath="/upload/item/shop/"+shopId+"/";
        return imagePath.replace("/",separator);
    }

    public static String getHeadLineImagePath() {
        String imagePath = "/upload/images/item/headtitle/";
        return imagePath.replace("/", separator);
    }
    public static String getShopCategoryPath() {
        String imagePath = "/upload/images/item/shopcategory/";
        return imagePath.replace("/", separator);
    }
}
