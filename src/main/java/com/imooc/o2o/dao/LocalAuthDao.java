package com.imooc.o2o.dao;

import com.imooc.o2o.entity.LocalAuth;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface LocalAuthDao {
    /**
     * 通过账号密码查询对应信息，登录用
     */
    LocalAuth queryLocalByUsernameAndPwd(@Param("username")String username, @Param("password")String password);

    /**
     * 通过用户id查询对应信息
     * @param userId
     * @return
     */
    LocalAuth queryLocalByUserId(@Param("userId")long userId);

    /**
     * 添加账号
     * @param localAuth
     * @return
     */
    int insertLocalAuth(LocalAuth localAuth);

    /**
     * 通过userId,username,password修改密码
     * @param userId
     * @param username
     * @param password
     * @param newPassword
     * @param lastEditTime
     * @return
     */
    int updateLocalAuth(@Param("userId")long userId, @Param("username")String username, @Param("password")String password, @Param("newPassword")String newPassword, @Param("lastEditTime")Date lastEditTime);
}
