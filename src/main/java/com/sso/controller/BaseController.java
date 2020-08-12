package com.sso.controller;

import com.baomidou.kisso.SSOHelper;
import com.baomidou.kisso.security.token.SSOToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author chenhaowen
 * @Description: 控制器抽象类
 * @date 2020/8/12 8:40 PM
 */
public abstract class BaseController {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    private HttpServletRequest request;
    private HttpServletResponse response;

    public BaseController() {
    }

    public HttpServletRequest getRequest() {
        return this.request;
    }

    @Resource
    public BaseController setRequest(HttpServletRequest request) {
        this.request = request;
        return this;
    }

    public HttpServletResponse getResponse() {
        return this.response;
    }

    @Resource
    public BaseController setResponse(HttpServletResponse response) {
        this.response = response;
        return this;
    }

    public Integer getLoginUserId(HttpServletRequest request, HttpServletResponse response) {
        SSOToken st = SSOHelper.getSSOToken(request);
        this.logger.info("SSOHelper 获取用户userId getId={}", st == null ? null : Integer.valueOf(st.getId()));
        return st == null ? null : Integer.valueOf(st.getId());
    }

    public Integer getLoginUserId() {
        return this.getLoginUserId(this.request, (HttpServletResponse)null);
    }

    public String getUserName() {
        SSOToken ssoToken = SSOHelper.getSSOToken(this.request);
        String userName = ssoToken.getIssuer();
        this.logger.info("获取用户名 => {}", userName);
        return userName;
    }

    public String getIp(HttpServletRequest request) {
        this.logger.debug("x-forwarded-for = {}", request.getHeader("x-forwarded-for"));
        String ip = request.getHeader("x-forwarded-for");
        String[] array = ip.split(",");
        this.logger.debug(array[0]);
        ip = array[0];
        this.logger.debug("X-real-ip = {}", ip);
        return ip;
    }

    public String getIp() {
        return this.getIp(this.getRequest());
    }

    protected void tip(String msg) {
        String tip = "<html><body><h1 style='text-align:center;margin-top:100px;'>%s</h1></html></body>";
        this.getResponse().setHeader("content-Type", "text/html; charset=utf-8");

        try {
            this.getResponse().getOutputStream().write(String.format(tip, msg).getBytes());
            this.getResponse().getOutputStream().close();
        } catch (Exception var4) {
        }

    }
}
