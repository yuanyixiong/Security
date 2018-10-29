package com.imooc.security.croe.validate.code.sms.sender;

import org.springframework.stereotype.Service;

/**
 * @author 袁毅雄
 * @description 发送短息的实现
 * @date 2018/10/20
 */
@Service
public class SmsCodeSenderImpl implements SmsCodeSender {
    @Override
    public boolean send(String mobile, String code) {
        System.out.println("更高级的短信验证码发送平台");
        System.out.println(String.format("%s,%s", mobile, code));
        return true;
    }
}
