package com.imooc.security.croe.validate.code;

import com.imooc.security.croe.properties.SecurityProperties;
import com.imooc.security.croe.validate.code.Image.ImageCodeGenertor;
import com.imooc.security.croe.validate.code.sms.sender.DefaultSmsCodeSenderImpl;
import com.imooc.security.croe.validate.code.sms.SmsCodeGenertor;
import com.imooc.security.croe.validate.code.sms.sender.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 袁毅雄
 * @description 验证码配置
 * @date 2018/10/18
 */
@Configuration
public class ValidateCodeBeanConfig {

    @Autowired
    private SecurityProperties securityProperties;

    @Bean
    @ConditionalOnMissingBean(name = "imageCodeGenertor")
    public ValidateCodeGenertor imageCodeGenertor() {
        ImageCodeGenertor imageCodeGenertor = new ImageCodeGenertor();
        imageCodeGenertor.setSecurityProperties(securityProperties);
        return imageCodeGenertor;
    }

    @Bean
    @ConditionalOnMissingBean(name = "smsCodeGenertor")
    public ValidateCodeGenertor smsCodeGenertor() {
        SmsCodeGenertor smsCodeGenertor = new SmsCodeGenertor();
        smsCodeGenertor.setSecurityProperties(securityProperties);
        return smsCodeGenertor;
    }

    @Bean
    @ConditionalOnMissingBean(SmsCodeSender.class)
    public SmsCodeSender smsCodeSender() {
        return new DefaultSmsCodeSenderImpl();
    }
}
