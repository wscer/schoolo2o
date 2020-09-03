package com.imooc.o2o.service;

import com.imooc.o2o.dto.LocalAuthExecution;
import com.imooc.o2o.entity.LocalAuth;
import com.imooc.o2o.exceptions.LocalAuthOperationException;

public interface LocalAuthService {
    /**
     * 根据用户名和密码获得信息
     * @param username
     * @param password
     * @return
     */
    LocalAuth getLocalAuthByUsernameAndPwd(String username,String password);

    /**
     * 通过userId获取平台账号信息
     * @param userId
     * @return
     */
    LocalAuth getLocalAuthByUserId(long userId);

    /**
     * 绑定微信账号，生成平台专属账号
     * @param localAuth
     * @return
     * @throws LocalAuthOperationException
     */
    LocalAuthExecution bindLocalAuth(LocalAuth localAuth) throws LocalAuthOperationException;

    /**
     * 更新密码
     * @param userId
     * @param username
     * @param password
     * @param newPassword
     * @return
     * @throws LocalAuthOperationException
     */
    LocalAuthExecution modifyLocalAuth(Long userId,String username,String password,String newPassword) throws LocalAuthOperationException;
}
