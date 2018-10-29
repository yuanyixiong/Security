package com.imooc.security.croe.validate.code.sms.sender;

/**
 * @author 袁毅雄
 * @description 发送短息的默认实现
 * @date 2018/10/20
 */
public class DefaultSmsCodeSenderImpl implements SmsCodeSender {
    @Override
    public boolean send(String mobile, String code) {
        System.out.println(String.format("%s,%s", mobile, code));
        return true;
    }
}
