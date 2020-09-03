package com.imooc.o2o.dao;

import com.imooc.o2o.entity.ProductImg;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductImgDao {
    /**
     * 批量增加商品图片的方法
     * @param productImgList
     * @return
     */
    int batchInsertProductImg(List<ProductImg> productImgList);

    /**
     * 删除指定商品下的所有详情图片
     * @param productId
     * @return
     */
    int deleteProductImgByProductId(long productId);

    /**
     * 根据传入的商品Id查询详情图片
     * @param productId
     * @return
     */
    List<ProductImg> queryProductImgList(@Param("productId") long productId);
}
