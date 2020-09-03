package com.imooc.o2o.service.impl;

import com.imooc.o2o.dao.ShopDao;
import com.imooc.o2o.dto.ImageHolder;
import com.imooc.o2o.dto.ShopExecution;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.enums.ShopStateEnum;
import com.imooc.o2o.exceptions.ShopOperationException;
import com.imooc.o2o.service.ShopService;
import com.imooc.o2o.util.ImageUtil;
import com.imooc.o2o.util.PageCalculator;
import com.imooc.o2o.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {
    @Autowired
    private ShopDao shopDao;

    /**
     * 获取店铺列表
     * @param shopCondition
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @Override
    public ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize) {
        int rowIndex= PageCalculator.calculatorRowIndex(pageIndex,pageSize);
        List<Shop> shopList=shopDao.queryShopList(shopCondition,rowIndex,pageSize);
        int count=shopDao.queryShopCount(shopCondition);
        ShopExecution se=new ShopExecution();
        if (shopList!=null){
            se.setShopList(shopList);
            se.setCount(count);
        }else {
            se.setState(ShopStateEnum.INNER_ERROR.getState());
        }
        return se;
    }

    /**
     * 通过店铺id获取店铺
     * @param shopId
     * @return
     */
    @Override
    public Shop getByShopId(long shopId) {
        return shopDao.queryShopById(shopId);
    }

    /**
     * 修改店铺信息
     * @param shop
     * @param thumbnail
     * @return
     * @throws ShopOperationException
     */
    @Override
    public ShopExecution modifyShop(Shop shop, ImageHolder thumbnail) throws ShopOperationException {
        try{
        if (shop==null||shop.getShopId()==null){
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        }else {
            //1.判断是否有图片
            if(thumbnail!=null&&thumbnail.getImageName()!=null&&!"".equals(thumbnail.getImageName())){
                Shop tempShop=shopDao.queryShopById(shop.getShopId());
                if(tempShop.getShopImg()!=null){
                    ImageUtil.deleteFileOrPath(tempShop.getShopImg());
                }
                addShopImg(shop,thumbnail);
            }
            //2.更新店铺信息
            shop.setLastEditTime(new Date());
            int effectedNum=shopDao.updateShop(shop);
            if (effectedNum<=0){
                return new ShopExecution(ShopStateEnum.INNER_ERROR);
            }else {
                shop=shopDao.queryShopById(shop.getShopId());
                return new ShopExecution(ShopStateEnum.SUCCESS,shop);
            }
        }}catch (Exception e){
            throw new ShopOperationException("modifyShop erroe"+e.getMessage());
        }
    }

    /**
     * 新增店铺
     * @param shop
     * @param thumbnail
     * @return
     */
    @Override
    @Transactional
    public ShopExecution addShop(Shop shop, ImageHolder thumbnail) {
        //控制判断，shop是否含有必须的值
        if (shop==null){
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        }
        try{
            //给店铺信息赋值
            shop.setEnableStatus(0);
            shop.setCreateTime(new Date());
            shop.setLastEditTime(new Date());
            int effectedNum=shopDao.insertShop(shop);
            if (effectedNum<=0){
                throw new ShopOperationException("店铺创建失败");
            }else {
                if (thumbnail!=null){
                    //存储图片
                    try {
                        addShopImg(shop, thumbnail);
                        shop.getShopImg();
                    }catch (Exception e){
                        throw new ShopOperationException("addShopImg error:"+e.getMessage());
                    }
                    //更新店铺的图片地址
                    effectedNum=shopDao.updateShop(shop);
                    if(effectedNum<=0){
                        throw new ShopOperationException("更新图片地址失败");
                    }

                }
            }

        }catch (Exception e){
            throw new ShopOperationException("addShop error"+e.getMessage());
        }
        return new ShopExecution(ShopStateEnum.CHECK,shop);
    }

    /**
     * 添加图片的方法
     * @param shop
     * @param thumbnail
     */
    private void addShopImg(Shop shop, ImageHolder thumbnail) {
        //获取shop图片目录的相对路径
        String dest= PathUtil.getShopImagePath(shop.getShopId());
        String shopImgAddr= ImageUtil.generateThumbnail(thumbnail,dest);
        shop.setShopImg(shopImgAddr);
    }
}
