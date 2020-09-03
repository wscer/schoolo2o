package com.imooc.o2o.dao;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.entity.HeadLine;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class HeadLineDaoTest extends BaseTest {
    @Autowired
    private HeadLineDao headLineDao;

    @Test
    public void testQueryHeadLineList(){
        List<HeadLine> headLineList=headLineDao.queryHeadLine(new HeadLine());
        assertEquals(2,headLineList.size());
    }
}
