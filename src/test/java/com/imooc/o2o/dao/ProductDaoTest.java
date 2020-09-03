package com.imooc.o2o.dao;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.entity.Product;
import com.imooc.o2o.entity.ProductCategory;
import com.imooc.o2o.entity.Shop;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ProductDaoTest extends BaseTest {
    @Autowired
    private ProductDao productDao;

    @Test
    public void testInsertProduct() throws Exception{
        Shop shop1=new Shop();
        shop1.setShopId(1L);
        ProductCategory productCategory=new ProductCategory();
        productCategory.setProductCategoryId(4L);

        Product product1=new Product();
        product1.setProductName("测试1");
        product1.setProductDesc("测试描述1");
        product1.setImgAddr("测试地址1");
        product1.setPriority(1);
        product1.setEnableStatus(1);
        product1.setCreateTime(new Date());
        product1.setLastEditTime(new Date());
        product1.setShop(shop1);
        product1.setProductCategory(productCategory);

        Product product2=new Product();
        product2.setProductName("测试2");
        product2.setProductDesc("测试描述2");
        product2.setImgAddr("测试地址2");
        product2.setPriority(1);
        product2.setEnableStatus(1);
        product2.setCreateTime(new Date());
        product2.setLastEditTime(new Date());
        product2.setShop(shop1);
        product2.setProductCategory(productCategory);

        int effectedNum=productDao.insertProduct(product1);
        assertEquals(1,effectedNum);
        effectedNum=productDao.insertProduct(product2);
        assertEquals(1,effectedNum);
    }

    @Test
    public void testQueryProductByProductId() throws Exception{
        long productId=7L;
        Product product=productDao.queryProductById(productId);
        assertEquals(2,product.getProductImgList().size());
 }

    @Test
    public void testUpdateProduct() throws Exception{
        Product product=new Product();
        ProductCategory productCategory=new ProductCategory();
        Shop shop=new Shop();
        shop.setShopId(1L);
        productCategory.setProductCategoryId(6L);
        product.setProductId(1L);
        product.setShop(shop);
        product.setProductName("测试商品2");
        product.setProductCategory(productCategory);

        int effectedNum=productDao.updateProduct(product);
        assertEquals(1,effectedNum);

    }

    @Test
    public void testQueryProductList() throws Exception{
        Product product=new Product();
        ProductCategory productCategory=new ProductCategory();
        Shop shop=new Shop();
        shop.setShopId(1L);
        productCategory.setProductCategoryId(6L);
        product.setShop(shop);
        product.setProductCategory(productCategory);
        Product product1=new Product();
        product1.setProductName("测试");

        List<Product> productList=productDao.queryProductList(product,0,2);
        int count=productDao.queryProductCount(product1);
        assertEquals(2,productList.size());
        assertEquals(4,count);
    }

    @Test
    public void testUpdateProductCategoryToNull(){
        int effectedNum=productDao.updateProductCategoryToNull(4L);
        assertEquals(2,effectedNum);
    }
}
