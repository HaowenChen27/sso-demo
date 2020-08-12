package com.sso.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sso.dto.LoginParam;
import com.sso.dto.RegisterParam;
import com.sso.model.SysUser;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chenhaowen
 * @since 2020-08-11
 */
public interface ISysUserService extends IService<SysUser> {

    /**
     * 注册
     * @param param
     * @return
     */
    boolean register(RegisterParam param);


    /**
     * 手机号是否已存在
     * @param tel
     * @return
     */
    boolean isTelExists(String tel);

    /**
     * 登录
     * @param param
     * @return
     */
    boolean login(LoginParam param);

    /**
     * 用户信息
     * @param userId
     * @return
     */
    SysUser userInfo(String userId);

    /**
     * 单点登录
     * @param param
     * @return
     */
    SysUser ssoLogin(LoginParam param);
}
