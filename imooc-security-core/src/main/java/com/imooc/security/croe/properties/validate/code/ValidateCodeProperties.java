package com.imooc.security.croe.properties.validate.code;

import lombok.Data;

/**
 * @author 袁毅雄
 * @description 自定义属性配置-验证码
 * @date 2018/10/18
 */
@Data
public class ValidateCodeProperties {

    /**
     * 图形验证码
     */
    private ImageCodeProperties imageCode = new ImageCodeProperties();

    /**
     * 短信验证码
     */
    private SmsCodeProperties smsCode = new SmsCodeProperties();

}
