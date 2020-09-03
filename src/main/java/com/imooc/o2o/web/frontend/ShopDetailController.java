package com.imooc.o2o.web.frontend;

import com.imooc.o2o.dto.ProductExecution;
import com.imooc.o2o.entity.Product;
import com.imooc.o2o.entity.ProductCategory;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.service.ProductCategoryService;
import com.imooc.o2o.service.ProductService;
import com.imooc.o2o.service.ShopService;
import com.imooc.o2o.util.HttpServletRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/frontend")
public class ShopDetailController {
    @Autowired
    private ShopService shopService;
    @Autowired
    private ProductCategoryService productCategoryService;
    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/listshopdetailpageinfo",method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> listShopDetailPageInfo(HttpServletRequest request){
        Map<String,Object> modelMap=new HashMap<>();
        //获取前台传来的shopId
        long shopId= HttpServletRequestUtil.getLong(request,"shopId");
        Shop shop=null;
        List<ProductCategory> productCategoryList=null;
        if (shopId!=-1){
            //获取店铺id为shopId的店铺信息
            shop=shopService.getByShopId(shopId);
            //获取店铺下的商品类别列表
            productCategoryList=productCategoryService.getProductCategoryListByShopId(shopId);
            modelMap.put("shop",shop);
            modelMap.put("productCategoryList",productCategoryList);
            modelMap.put("success",true);
        }else {
            modelMap.put("success",false);
            modelMap.put("errMsg","empty shopId");
        }
        return modelMap;
    }

    /**
     * 根据查询条件返回商品列表
     * @param request
     * @return
     */
    @RequestMapping(value = "/listproductsbyshop",method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> listProductsByShop(HttpServletRequest request){
        Map<String,Object> modelMap=new HashMap<>();
        //获取页码
        int pageIndex= HttpServletRequestUtil.getInt(request,"pageIndex");
        //获取每页显示的数量
        int pageSize= HttpServletRequestUtil.getInt(request,"pageSize");
        //获取店铺Id
        long shopId= HttpServletRequestUtil.getLong(request,"shopId");
        //空值判断
        if ((pageIndex>-1)&&(pageSize>-10)&&(shopId>-1)){
            //尝试获取商品类别Id
            long productCategoryId= HttpServletRequestUtil.getLong(request,"productCategoryId");
            //尝试获取模糊查找的商品名
            String productName= HttpServletRequestUtil.getString(request,"productName");
            //组合查询条件
            Product productCondition=compactShopCondition4Search(shopId,productCategoryId,productName);
            ProductExecution productExecution=productService.getProductList(productCondition,pageIndex,pageSize);
            modelMap.put("productList",productExecution.getProductList());
            modelMap.put("count",productExecution.getCount());
            modelMap.put("success",true);
        }else {
            modelMap.put("success",false);
            modelMap.put("errMsg","empty pageSize or pageIndex or shopId");
        }
        return modelMap;
    }

    private Product compactShopCondition4Search(long shopId, long productCategoryId, String productName) {
        Product productCondition=new Product();
        Shop shop=new Shop();
        shop.setShopId(shopId);
        if (productCategoryId!=-1L){
            //查询某个productCategory下面的店铺列表
            ProductCategory productCategory=new ProductCategory();
            productCategory.setProductCategoryId(productCategoryId);
            productCondition.setProductCategory(productCategory);
        }
        if (productName!=null){
            //查询名字里含有该信息的商品列表
            productCondition.setProductName(productName);
        }
        //前端展示的商品都是上架的商品
        productCondition.setEnableStatus(1);
        return productCondition;

    }
}
