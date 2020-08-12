package com.sso.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chenhaowen
 * @Description: 登录参数类
 * @date 2020/8/12 8:14 PM
 */
@Data
public class LoginParam implements Serializable {

    /**
     * 手机号
     */
    private String tel;

    /**
     * 密码
     */
    private String password;
}
