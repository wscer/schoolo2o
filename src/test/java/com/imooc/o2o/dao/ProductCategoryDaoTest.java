package com.imooc.o2o.dao;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.entity.ProductCategory;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductCategoryDaoTest extends BaseTest {
    @Autowired
    private ProductCategoryDao productCategoryDao;
    @Test
    public void testBQueryProductCategoryByShopId(){
        List<ProductCategory> productCategoryList= productCategoryDao.queryProductCategoryByShopId(1L);
        assertEquals(4,productCategoryList.size());

    }

    @Test
    public void testABatchInsertProductCategory(){
        ProductCategory productCategory=new ProductCategory();
        productCategory.setProductCategoryName("商品类别1");
        productCategory.setPriority(1);
        productCategory.setCreateTime(new Date());
        productCategory.setShopId(2L);
        ProductCategory productCategory2=new ProductCategory();
        productCategory2.setProductCategoryName("商品类别2");
        productCategory2.setPriority(2);
        productCategory2.setCreateTime(new Date());
        productCategory2.setShopId(2L);
        List<ProductCategory> productCategoryList=new ArrayList<>();
        productCategoryList.add(productCategory);
        productCategoryList.add(productCategory2);
        int effectedNum=productCategoryDao.batchInsertProductCategory(productCategoryList);
        assertEquals(2,effectedNum);
    }

    @Test
    public void testCDeleteProductCategory(){
        long shopId=2L;
        List<ProductCategory> productCategoryList=productCategoryDao.queryProductCategoryByShopId(shopId);
        for (ProductCategory productCategory:productCategoryList){
            if ("测试类别1".equals(productCategory.getProductCategoryName())||"测试类别2".equals(productCategory.getProductCategoryName())){
                int effectedNum=productCategoryDao.deleteProductCategory(productCategory.getProductCategoryId(),shopId);
                assertEquals(1,effectedNum);
            }
        }
    }
}
