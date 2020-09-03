package com.imooc.o2o.service;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.dto.ImageHolder;
import com.imooc.o2o.dto.ShopExecution;
import com.imooc.o2o.entity.Area;
import com.imooc.o2o.entity.PersonInfo;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.entity.ShopCategory;
import com.imooc.o2o.enums.ShopStateEnum;
import com.imooc.o2o.exceptions.ShopOperationException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class ShopServiceTest extends BaseTest {
    @Autowired
    private ShopService shopService;

    @Test
    public void testAddShop() throws FileNotFoundException {
        Shop shop = new Shop();
        PersonInfo owner=new PersonInfo();
        Area area=new Area();
        ShopCategory shopCategory=new ShopCategory();
        owner.setUserId(1L);
        area.setAreaId(2);
        shopCategory.setShopCategoryId(1L);
        shop.setOwner(owner);
        shop.setArea(area);
        shop.setShopCategory(shopCategory);
        shop.setShopName("测试的店铺1");
        shop.setShopDesc("test1");
        shop.setShopAddr("test1");
        shop.setPhone("test1");
        shop.setCreateTime(new Date());
        shop.setEnableStatus(ShopStateEnum.CHECK.getState());
        shop.setAdvice("审核中");
        File shopImg=new File("C:/Users/Wschaos/Desktop/fig/1.jpg");
        InputStream is=new FileInputStream(shopImg);
        ImageHolder imageHolder=new ImageHolder(shopImg.getName(),is);
        ShopExecution se=shopService.addShop(shop,imageHolder);
        assertEquals(ShopStateEnum.CHECK.getState(),se.getState());
    }

    @Test
    public void testModifyShop() throws ShopOperationException,FileNotFoundException{
        Shop shop =new Shop();
        shop.setShopId(1L);
        shop.setShopName("雍景轩");
        File shopImg=new File("C:/Users/Wschaos/Desktop/fig/1.jpg");
        InputStream is=new FileInputStream(shopImg);
        ImageHolder imageHolder=new ImageHolder(shopImg.getName(),is);
        ShopExecution shopExecution=shopService.modifyShop(shop,imageHolder);
        System.out.println("新的图片地址"+shopExecution.getShop().getShopImg());
    }
}
