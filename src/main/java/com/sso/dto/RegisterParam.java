package com.sso.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chenhaowen
 * @Description: 注册参数类
 * @date 2020/8/12 7:52 PM
 */
@Data
public class RegisterParam implements Serializable {

    /**
     * 昵称
     */
    private String name;

    /**
     * 手机号
     */
    private String tel;

    /**
     * 密码
     */
    private String password;
}
