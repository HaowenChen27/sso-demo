package com.sso.controller;

import com.baomidou.kisso.SSOHelper;
import com.baomidou.kisso.annotation.Action;
import com.baomidou.kisso.annotation.Login;
import com.baomidou.kisso.security.token.SSOToken;
import com.sso.dto.LoginParam;
import com.sso.model.SysUser;
import com.sso.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author chenhaowen
 * @Description: 单点登录控制器
 * @date 2020/8/12 8:35 PM
 */
@RestController
@RequestMapping("/sso")
public class SsoLoginController extends BaseController {

    @Resource
    private ISysUserService sysUserService;

    @RequestMapping("/index")
    @Login(action = Action.Skip)
    public String home() {
        return "Hello Kisso!";
    }


    @RequestMapping("/login")
    @Login(action = Action.Skip)
    public String login(String tel, String password) {
        LoginParam param = new LoginParam();
        param.setTel(tel);
        param.setPassword(password);
        SysUser user = sysUserService.ssoLogin(param);
        if (null == user) {
            return "login failed!";
        }
        // 设置登录 COOKIE
        SSOHelper.setCookie(this.getRequest(), this.getResponse(), SSOToken.create()
                .setIp(this.getRequest())
                .setId(user.getId())
                .setIssuer(user.getName()), false);
        return "login success!";
    }


    @RequestMapping("/token")
    public String token() {
        String msg = "暂未登录";
        SSOToken ssoToken = SSOHelper.attrToken(this.getRequest());
        if (null != ssoToken) {
            msg = "登录信息 ip=" + ssoToken.getIp();
            msg += "， id=" + ssoToken.getId();
            msg += "， issuer=" + ssoToken.getIssuer();
        }
        return msg;
    }


    @RequestMapping("/logout")
    public String logout() {
        SSOHelper.clearLogin(this.getRequest(), this.getResponse());
        return "Logout Kisso!";
    }


}
