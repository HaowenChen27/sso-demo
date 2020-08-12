package com.sso;

import com.sso.bootstrap.SsoApplication;
import com.sso.dao.SysUserMapper;
import com.sso.dto.LoginParam;
import com.sso.dto.RegisterParam;
import com.sso.service.ISysUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author chenhaowen
 * @Description:
 * @date 2020/8/11 11:44 AM
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SsoApplication.class)
public class UserTest {

    @Resource
    private ISysUserService sysUserService;

    @Resource
    private SysUserMapper sysUserMapper;

    @Test
    public void test1() {
//        SysUser sysUser = sysUserMapper.selectById(1);
//        System.out.println(JSONObject.toJSONString(sysUser));
        Long size = sysUserMapper.countSize();
        System.out.println(size);
    }

    @Test
    public void test2() {
        RegisterParam param = new RegisterParam();
        param.setTel("15951300471");
        param.setPassword("123456");
        boolean register = sysUserService.register(param);
        System.out.println(register);
    }


    @Test
    public void test3() {
        LoginParam param = new LoginParam();
        param.setTel("17521289427");
        param.setPassword("a111111");
        boolean login = sysUserService.login(param);
        System.out.println(login);
    }


}
