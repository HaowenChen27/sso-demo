package com.sso.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sso.dao.SysUserMapper;
import com.sso.dto.LoginParam;
import com.sso.dto.RegisterParam;
import com.sso.model.SysUser;
import com.sso.service.ISysUserService;
import com.sso.utils.Md5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chenhaowen
 * @since 2020-08-11
 */
@Service
@Validated
@Slf4j
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Override
    public boolean register(RegisterParam param) {
        if (null == param) {
            log.error("参数不能为空");
            return false;
        }
        if (isTelExists(param.getTel())) {
            log.error("手机号已经存在");
            return false;
        }
        String password = Md5Util.encode(param.getPassword());
        SysUser user = new SysUser();
        user.setPassword(password);
        user.setTel(param.getTel());
        //昵称默认手机号
        if (StringUtils.isEmpty(param.getName())) {
            user.setName(param.getTel());
        } else {
            user.setName(param.getName());
        }
        int insert = baseMapper.insert(user);
        return insert > 0;
    }

    @Override
    public boolean isTelExists(String tel) {
        LambdaQueryWrapper<SysUser> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(SysUser::getTel, tel);
        Integer count = baseMapper.selectCount(queryWrapper);
        return count > 0;
    }

    @Override
    public boolean login(LoginParam param) {
        if (null == param) {
            log.error("参数不能为空");
            return false;
        }
        LambdaQueryWrapper<SysUser> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(SysUser::getTel, param.getTel()).last("limit 1");
        SysUser user = baseMapper.selectOne(queryWrapper);
        if (null == user) {
            log.error("手机号不存在，请先注册");
            return false;
        }
        boolean success = Objects.equals(user.getPassword(), Md5Util.encode(param.getPassword()));
        if (success) {
            log.info("{} 登录成功！", user.getName());
            return true;
        }
        log.info("用户名密码不正确");
        return false;
    }

    @Override
    public SysUser userInfo(String tel) {
        LambdaQueryWrapper<SysUser> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(SysUser::getTel, tel).last("limit 1");
        return baseMapper.selectOne(queryWrapper);
    }

    @Override
    public SysUser ssoLogin(LoginParam param) {
        if (login(param)) {
            return userInfo(param.getTel());
        }
        return null;
    }
}
