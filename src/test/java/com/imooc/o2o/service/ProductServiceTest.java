package com.imooc.o2o.service;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.dto.ImageHolder;
import com.imooc.o2o.dto.ProductExecution;
import com.imooc.o2o.entity.Product;
import com.imooc.o2o.entity.ProductCategory;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.enums.ProductStateEnum;
import com.imooc.o2o.exceptions.ShopOperationException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ProductServiceTest extends BaseTest {
    @Autowired
    private ProductService productService;

    @Test
    public void testAddProduct() throws ShopOperationException, FileNotFoundException{
        Product product=new Product();
        Shop shop=new Shop();
        shop.setShopId(1L);
        ProductCategory productCategory=new ProductCategory();
        productCategory.setProductCategoryId(4L);

        product.setShop(shop);
        product.setProductCategory(productCategory);
        product.setProductName("测试商品1");
        product.setProductDesc("测试商品1");
        product.setPriority(20);
        product.setCreateTime(new Date());
        product.setEnableStatus(ProductStateEnum.SUCCESS.getState());

        //创建缩略图文件
        File thumbnailFile= new File("C:/Users/Wschaos/Desktop/fig/2.jpg");
        InputStream is=new FileInputStream(thumbnailFile);
        ImageHolder thumbnail=new ImageHolder(thumbnailFile.getName(),is);

        //创建两个商品详情图片文件并将它们添加到详情图列表中
        File productImg1=new File("C:/Users/Wschaos/Desktop/fig/3.jpg");
        InputStream is2=new FileInputStream(productImg1);
        File productImg2=new File("C:/Users/Wschaos/Desktop/fig/4.jpg");
        InputStream is3=new FileInputStream(productImg2);

        List<ImageHolder> productImgList=new ArrayList<>();
        productImgList.add(new ImageHolder(productImg1.getName(),is2));
        productImgList.add(new ImageHolder(productImg2.getName(),is3));

        //添加商品并验证
        ProductExecution productExecution=productService.addProduct(product,thumbnail,productImgList);
        assertEquals(ProductStateEnum.SUCCESS.getState(),productExecution.getState());
    }

    @Test
    public void testModifyProduct() throws ShopOperationException,FileNotFoundException{
        Product product=new Product();
        Shop shop=new Shop();
        ProductCategory productCategory=new ProductCategory();
        shop.setShopId(1L);
        productCategory.setProductCategoryId(6L);
        product.setProductId(4L);
        product.setShop(shop);
        product.setProductCategory(productCategory);
        product.setProductName("正式商品");
        product.setProductDesc("正式商品");
        //创建文件缩略图
        File thumbnailFile= new File("C:/Users/Wschaos/Desktop/fig/4.jpg");
        InputStream is=new FileInputStream(thumbnailFile);
        ImageHolder thumbnail=new ImageHolder(thumbnailFile.getName(),is);

        //创建两个商品详情图片文件并将它们添加到详情图列表中
        File productImg1=new File("C:/Users/Wschaos/Desktop/fig/3.jpg");
        InputStream is2=new FileInputStream(productImg1);
        File productImg2=new File("C:/Users/Wschaos/Desktop/fig/2.jpg");
        InputStream is3=new FileInputStream(productImg2);

        List<ImageHolder> productImgList=new ArrayList<>();
        productImgList.add(new ImageHolder(productImg1.getName(),is2));
        productImgList.add(new ImageHolder(productImg2.getName(),is3));

        ProductExecution productExecution=productService.modifyProduct(product,thumbnail,productImgList);
        assertEquals(ProductStateEnum.SUCCESS.getState(),productExecution.getState());
    }
}
