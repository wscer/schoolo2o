package com.imooc.o2o.dao;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.entity.Area;
import com.imooc.o2o.entity.PersonInfo;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.entity.ShopCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ShopDaoTest extends BaseTest {
    @Autowired
    private ShopDao shopDao;
    @Test
    public void testInsertShop(){
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
        shop.setShopName("测试的店铺");
        shop.setShopDesc("test");
        shop.setShopAddr("test");
        shop.setPhone("test");
        shop.setShopImg("test");
        shop.setCreateTime(new Date());
        shop.setEnableStatus(1);
        shop.setAdvice("审核中");
        int effectNum=shopDao.insertShop(shop);
        assertEquals(1,effectNum);
    }

    @Test
    public void testUpdateShop(){
        Shop shop = new Shop();
        shop.setShopId(1L);
        shop.setShopName("测试的店铺");
        shop.setShopDesc("测试描述");
        shop.setShopAddr("测试地址");
        shop.setPhone("测试电话");
        shop.setShopImg("测试图片");
        shop.setEnableStatus(1);
        shop.setAdvice("审核中");
        int effectNum=shopDao.updateShop(shop);
        assertEquals(1,effectNum);
    }

    @Test
    public void testQueryShopById(){
        long shopId=1L;
        Shop shop=shopDao.queryShopById(shopId);
        System.out.println(shop.getArea().getAreaId());
        System.out.println(shop.getArea().getAreaName());
    }

    @Test
    public void testQueryShopList(){
        Shop shopCondition=new Shop();
        ShopCategory parentShopCategory=new ShopCategory();
        ShopCategory childShopCategory=new ShopCategory();
        parentShopCategory.setShopCategoryId(1L);
        childShopCategory.setParent(parentShopCategory);
        shopCondition.setShopCategory(childShopCategory);
        List<Shop> shopList=shopDao.queryShopList(shopCondition,0,5);
        int count=shopDao.queryShopCount(shopCondition);
        System.out.println("店铺列表的大小："+shopList.size()+"  "+count);
    }


}
