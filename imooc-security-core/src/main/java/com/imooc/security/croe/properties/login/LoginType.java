package com.imooc.security.croe.properties.login;

/**
 * @author 袁毅雄
 * @description 返回模式
 * @date 2018/9/30
 */
public enum LoginType {

    /**
     * 返回模式 JOSN
     */
    JSON("返回Json"),
    /**
     * 返回模式 跳转界面
     */
    REDIRECT("跳转界面");

    private String describe;

    LoginType(String describe) {
        this.describe = describe;
    }
}
