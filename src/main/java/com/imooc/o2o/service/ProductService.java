package com.imooc.o2o.service;

import com.imooc.o2o.dto.ImageHolder;
import com.imooc.o2o.dto.ProductExecution;
import com.imooc.o2o.entity.Product;
import com.imooc.o2o.exceptions.ProductOperationException;

import java.util.List;

public interface ProductService {
    /**
     * 添加商品信息及图片处理
     * @param product
     * @param thumbnail
     * @param productImgList
     * @return
     * @throws ProductOperationException
     */
    ProductExecution addProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgList)throws ProductOperationException;

    /**
     * 通过商品Id查询唯一的商品信息
     * @param productId
     * @return
     */
    Product getProductById(long productId);

    /**
     * 修改商品信息以及商品图片处理
     * @param product
     * @param thumbnail
     * @param productImgHolderList
     * @return
     */
    ProductExecution modifyProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgHolderList) throws ProductOperationException;

    /**
     * 分页查询商品列表，可输入的条件有：商品名（模糊），商品状态，店铺状态，店铺Id，商品类别
     * @param productCondition
     * @param pageIndex
     * @param pageSize
     * @return
     */
    ProductExecution getProductList(Product productCondition, int pageIndex, int pageSize);
}
