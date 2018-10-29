package com.imooc.security.croe.properties.validate.code;

import lombok.Data;

/**
 * @author 袁毅雄
 * @description 自定义属性配置-短信验证码配置
 * @date 2018/10/18
 */
@Data
public class SmsCodeProperties {

    /**
     * 验证码数字位数
     */
    Integer length = 6;

    /**
     * 验证码失效时间
     */
    Integer expireTime = 60;

    /**
     * 请求接口
     */
    String url;
}
