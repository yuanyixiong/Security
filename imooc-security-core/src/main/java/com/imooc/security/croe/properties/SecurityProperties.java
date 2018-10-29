package com.imooc.security.croe.properties;

import com.imooc.security.croe.properties.login.BrowserProperties;
import com.imooc.security.croe.properties.validate.code.ValidateCodeProperties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author 袁毅雄
 * @description 自定义属性配置
 * @date 2018/9/30
 */
@Data
@ConfigurationProperties(prefix = "imooc.security")
public class SecurityProperties {

    /**
     * 浏览器配置
     */
    private BrowserProperties browser = new BrowserProperties();

    /**
     * 验证码配置
     */
    private ValidateCodeProperties validateCode = new ValidateCodeProperties();
}
