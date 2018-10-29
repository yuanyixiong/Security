package com.imooc.security.croe.validate.code.sms.sender;

/**
 * @author 袁毅雄
 * @description 发送短信
 * @date 2018/10/20
 */
public interface SmsCodeSender {

    /**
     * 发送短信
     * @param mobile 手机
     * @param code 短信验证码
     * @return
     */
    boolean send(String mobile, String code);
}
