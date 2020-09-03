package com.imooc.o2o.service;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.entity.ProductCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ProductCategoryServiceTest extends BaseTest {
    @Autowired
    private ProductCategoryService productCategoryService;
    @Test
    public void testGetProductCategoryListByShopId(){
        List<ProductCategory> productCategoryList=productCategoryService.getProductCategoryListByShopId(1L);
        assertEquals("测试类别2",productCategoryList.get(0).getProductCategoryName());
    }
}
