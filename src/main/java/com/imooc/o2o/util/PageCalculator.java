package com.imooc.o2o.util;

public class PageCalculator {
    /**
     * 页码与数据款信息条数之间的转换关系
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public static int calculatorRowIndex(int pageIndex,int pageSize){
        return (pageIndex>0)?(pageIndex-1)*pageSize:0;
    }
}
