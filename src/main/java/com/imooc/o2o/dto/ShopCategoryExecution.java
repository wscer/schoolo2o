package com.imooc.o2o.dto;

import java.util.List;

import com.imooc.o2o.entity.ShopCategory;
import com.imooc.o2o.enums.ShopCategoryStateEnum;

public class ShopCategoryExecution {
	// 结果状态
	private int state;

	// 状态标识
	private String stateInfo;
	//操作的商铺类型
	private ShopCategory shopCategory;

	// 操作的商铺类型列表
	private List<ShopCategory> shopCategoryList;

	public ShopCategoryExecution() {
	}

	/**
	 * 店铺操作失败时使用的构造器
	 * @param stateEnum
	 */
	public ShopCategoryExecution(ShopCategoryStateEnum stateEnum) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}

	/**
	 * 操作成功时使用的构造器
	 * @param stateEnum
	 * @param shopCategory 传入商铺类型
	 */
	public ShopCategoryExecution(ShopCategoryStateEnum stateEnum, ShopCategory shopCategory) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.shopCategory = shopCategory;
	}

	/**
	 * 操作成功时使用的构造器
	 * @param stateEnum
	 * @param shopCategoryList 传入商铺类型列表
	 */
	public ShopCategoryExecution(ShopCategoryStateEnum stateEnum, List<ShopCategory> shopCategoryList) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.shopCategoryList = shopCategoryList;
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

	public ShopCategory getShopCategory() {
		return shopCategory;
	}

	public void setShopCategory(ShopCategory shopCategory) {
		this.shopCategory = shopCategory;
	}

	public List<ShopCategory> getShopCategoryList() {
		return shopCategoryList;
	}

	public void setShopCategoryList(List<ShopCategory> shopCategoryList) {
		this.shopCategoryList = shopCategoryList;
	}

}
