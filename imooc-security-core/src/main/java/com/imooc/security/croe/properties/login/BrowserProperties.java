package com.imooc.security.croe.properties.login;

import lombok.Data;

/**
 * @author 袁毅雄
 * @description 自定义属性配置-浏览器配置
 * @date 2018/9/30
 */
@Data
public class BrowserProperties {

    /**
     * 默认的登录页面
     */
    private String loginPage = "/imooc-signIn.html";

    /**
     * 页面返回方式
     */
    private LoginType loginType = LoginType.JSON;

}
