package com.imooc.o2o.dto;

import com.imooc.o2o.entity.ProductCategory;
import com.imooc.o2o.enums.ProductCategoryStateEnum;

import java.util.List;

public class ProductCategoryExecution {
    //结果状态
    private int state;

    //状态标识
    private String stateInfo;

    private List<ProductCategory> productCategoryList;

    public ProductCategoryExecution(){

    }

    /**
     * 操作失败的时候使用的构造器
     */

    public ProductCategoryExecution(ProductCategoryStateEnum productCategoryStateEnum){
        this.state=productCategoryStateEnum.getState();
        this.stateInfo=productCategoryStateEnum.getStateInfo();
    }

    /**
     * 操作成功时的构造器
     * @param productCategoryStateEnum
     * @param productCategoryList 传入商品类型列表
     */
    public ProductCategoryExecution(ProductCategoryStateEnum productCategoryStateEnum,List<ProductCategory> productCategoryList){
        this.state=productCategoryStateEnum.getState();
        this.stateInfo=productCategoryStateEnum.getStateInfo();
        this.productCategoryList=productCategoryList;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public List<ProductCategory> getProductCategoryList() {
        return productCategoryList;
    }

    public void setProductCategoryList(List<ProductCategory> productCategoryList) {
        this.productCategoryList = productCategoryList;
    }
}
