package com.imooc.o2o.web.frontend;

import com.imooc.o2o.dto.ShopExecution;
import com.imooc.o2o.entity.Area;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.entity.ShopCategory;
import com.imooc.o2o.service.AreaService;
import com.imooc.o2o.service.ShopCategoryService;
import com.imooc.o2o.service.ShopService;
import com.imooc.o2o.util.HttpServletRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/frontend")
public class ShopListController {
    @Autowired
    private AreaService areaService;
    @Autowired
    private ShopCategoryService shopCategoryService;
    @Autowired
    private ShopService shopService;

    /**
     * 根据传入条件显示一级列表或者二级列表
     * @param request
     * @return
     */
    @RequestMapping(value = "/listshopspageinfo",method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> listShopPageInfo(HttpServletRequest request){
        Map<String,Object> modelMap=new HashMap<>();
        //试着从前端获取parentId
        long parentId= HttpServletRequestUtil.getLong(request,"parentId");
        List<ShopCategory> shopCategoryList=null;
        if (parentId!=-1){
            //如果parentId存在，则取出该一级ShopCate下的二级列表
            try {
                ShopCategory shopCategoryCondition=new ShopCategory();
                ShopCategory parentShopCategory=new ShopCategory();
                parentShopCategory.setShopCategoryId(parentId);
                shopCategoryCondition.setParent(parentShopCategory);
                shopCategoryList=shopCategoryService.getShopCategoryList(shopCategoryCondition);
            }catch (Exception e){
                modelMap.put("success",false);
                modelMap.put("errMsg",e.getMessage());
            }
        }else {
            try {
                //如果parentId不存在，则取出所有一级shopCategory
                shopCategoryList=shopCategoryService.getShopCategoryList(null);
            }catch (Exception e){
                modelMap.put("success",false);
                modelMap.put("errMsg",e.getMessage());
            }
        }
        modelMap.put("shopCategoryList",shopCategoryList);
        List<Area> areaList=new ArrayList<>();
        try {
            //获取区域列表
            areaList=areaService.getAreaList();
            modelMap.put("areaList",areaList);
            modelMap.put("success",true);
        }catch (Exception e){
            modelMap.put("success",false);
            modelMap.put("errMsg",e.getMessage());
        }
        return modelMap;
    }

    /**
     * 获取指定条件下的店铺列表
     * @param request
     * @return
     */
    @RequestMapping(value = "/listshops",method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> listShops(HttpServletRequest request){
        Map<String,Object> modelMap=new HashMap<>();
        //获取页码
        int pageIndex= HttpServletRequestUtil.getInt(request,"pageIndex");
        //获取每页显示的条数
        int pageSize= HttpServletRequestUtil.getInt(request,"pageSize");
        //非空判断
        if ((pageIndex>-1)&&(pageSize>-1)){
            //试着获取一级类别Id
            long parentId= HttpServletRequestUtil.getLong(request,"parentId");
            //试着获取特定二级类别Id
            long shopCategoryId= HttpServletRequestUtil.getLong(request,"shopCategoryId");
            //试着获取区域Id
            int areaId= HttpServletRequestUtil.getInt(request,"areaId");
            //试着获取模糊字段查询
            String shopName= HttpServletRequestUtil.getString(request,"shopName");
            //获取组合之后的查询条件
            Shop shopCondition=compactShopCondition4Search(parentId,shopCategoryId,areaId,shopName);
            //根据查询条件和分页信息获取店铺列表，并返回总数
            ShopExecution shopExecution=shopService.getShopList(shopCondition,pageIndex,pageSize);
            modelMap.put("shopList",shopExecution.getShopList());
            modelMap.put("count",shopExecution.getCount());
            modelMap.put("success",true);

        }else {
            modelMap.put("success",false);
            modelMap.put("errMsg","empty pageSize or PageIndex");
        }
        return modelMap;
    }

    private Shop compactShopCondition4Search(long parentId, long shopCategoryId, int areaId, String shopName) {
        Shop shopCondition=new Shop();
        if (parentId!=-1){
            //查询某个一级ShopCategory下面的所有二级ShopCategory里面的店铺
            ShopCategory parentCategory=new ShopCategory();
            parentCategory.setShopCategoryId(parentId);
            ShopCategory childCategory=new ShopCategory();
            childCategory.setParent(parentCategory);
            shopCondition.setShopCategory(childCategory);
        }
        if (shopCategoryId!=-1L){
            //查询某个二级ShopCategory下面的店铺列表
            ShopCategory shopCategory=new ShopCategory();
            shopCategory.setShopCategoryId(shopCategoryId);
            shopCondition.setShopCategory(shopCategory);
        }
        if (areaId!=-1L){
            //查询位于某个区域的店铺列表
            Area area=new Area();
            area.setAreaId(areaId);
            shopCondition.setArea(area);
        }
        if (shopName!=null){
            //查询名字里含有该信息的店铺列表
            shopCondition.setShopName(shopName);
        }
        //前端展示的店铺都是审核成功的店铺
        shopCondition.setEnableStatus(1);
        return shopCondition;

    }
}
