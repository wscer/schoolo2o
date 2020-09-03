package com.imooc.o2o.util;

import com.imooc.o2o.dto.ImageHolder;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;


public class ImageUtil {
    //水印图片地址
    private static String basePath="E:/IDEA WorkSpace/o2o/src/main/resources";

    //定义时间格式
    private static final SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyyMMddHHmmss");

    //定义随机数
    private static final Random r=new Random();

    //日志信息
    private static Logger logger= LoggerFactory.getLogger(ImageUtil.class);

    /**
     * 将CommonsMultipartFile转换成File
     * @param cFile
     * @return
     */
    public static File transferCommonsMultipartFileToFile(CommonsMultipartFile cFile){
        File newFile= new File(cFile.getOriginalFilename());

        try {
            cFile.transferTo(newFile);
        }catch (IllegalStateException e){
            logger.debug(e.toString());
            e.printStackTrace();
        } catch (IOException e) {
            logger.debug(e.toString());
            e.printStackTrace();
        }
        return newFile;
    }

    /**
     * 处理缩略图，并返回新生成图片的相对路径
     * @param thumbnail
     * @param targetAddr
     * @return
     */
    public static String generateThumbnail(ImageHolder thumbnail, String targetAddr){
        String realFileName=getRandomFileName();
        String extension=getFileExtension(thumbnail.getImageName());
        makeDirPath(targetAddr);
        String relativeAddr=targetAddr+realFileName+extension;
        logger.debug("current relativeAddr is"+relativeAddr);
        File dest=new File(PathUtil.getImgBasePath()+relativeAddr);
        logger.debug("current complete addr is"+PathUtil.getImgBasePath()+relativeAddr);
        try {
            Thumbnails.of(thumbnail.getImage()).size(200,200).watermark(Positions.BOTTOM_RIGHT,ImageIO.read(new File(basePath+"/timg.jpg")),0.25f).outputQuality(0.8f).toFile(dest);
        }catch (IOException e){
            logger.error(e.toString());
            e.printStackTrace();
        }
        return relativeAddr;
    }

    /**
     * 创建目标路径所涉及的目录，
     * @param targetAddr
     */
    private static void makeDirPath(String targetAddr) {
        String realFileParentPath=PathUtil.getImgBasePath()+targetAddr;
        File dirPath=new File(realFileParentPath);
        if (!dirPath.exists()){
            dirPath.mkdirs();
        }
    }

    /**
     * 获取输入文件流的扩展名
     * @param fileName 输入的文件流
     * @return
     */
    private static String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    /**
     * 生产随机文件名，当前年月日小时分钟秒钟+5位随机数
     * @return
     */
    public static String getRandomFileName() {
        //获取随机的5位数
        int ranNum=r.nextInt(89999)+10000;
        String nowTimeStr=sDateFormat.format(new Date());
        return nowTimeStr+ranNum;
    }

    /**
     * storePath是文件的路径还是目录的路径，
     * 如果是文件路径则删除该文件，
     * 如果是目录路径则删除该目录下的所有文件
     * @param storePath
     */
    public static void deleteFileOrPath(String storePath){
        File fileOrPath=new File(PathUtil.getImgBasePath()+storePath);
        if (fileOrPath.exists()){
            if (fileOrPath.isDirectory()){
                File files[]=fileOrPath.listFiles();
                for (int i=0;i<files.length;i++){
                    files[i].delete();
                }
            }
        }
        fileOrPath.delete();
    }

    /**
     * 生成商品详情图片的方法
     * @param thumbnail
     * @param targetAddr
     * @return
     */
    public static String generateNormalImg(ImageHolder thumbnail, String targetAddr) {
        String realFileName=getRandomFileName();
        String extension=getFileExtension(thumbnail.getImageName());
        makeDirPath(targetAddr);
        String relativeAddr=targetAddr+realFileName+extension;
        logger.debug("current relativeAddr is"+relativeAddr);
        File dest=new File(PathUtil.getImgBasePath()+relativeAddr);
        logger.debug("current complete addr is"+PathUtil.getImgBasePath()+relativeAddr);
        try {
            Thumbnails.of(thumbnail.getImage()).size(337,640).watermark(Positions.BOTTOM_RIGHT,ImageIO.read(new File(basePath+"/timg.jpg")),0.25f).outputQuality(0.9f).toFile(dest);
        }catch (IOException e){
            logger.error(e.toString());
            e.printStackTrace();
        }
        return relativeAddr;
    }
}
