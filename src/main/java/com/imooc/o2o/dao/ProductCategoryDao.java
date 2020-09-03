package com.imooc.o2o.dao;

import com.imooc.o2o.entity.ProductCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductCategoryDao {
    /**
     * 查询商品类别
     * @return
     */
    List<ProductCategory> queryProductCategoryByShopId(long shopId);

    /**
     * 批量新增商品类别
     * @param productCategoryList
     * @return
     */
    int batchInsertProductCategory(List<ProductCategory> productCategoryList);

    /**
     * 删除商品类别
     * @param productCategoryId 商品类别id
     * @param shopId 店铺id
     * @return effectedNum
     */
    int deleteProductCategory(@Param("productCategoryId")long productCategoryId,@Param("shopId")long shopId);
}
