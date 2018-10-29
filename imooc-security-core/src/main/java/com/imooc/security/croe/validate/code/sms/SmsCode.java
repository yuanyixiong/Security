package com.imooc.security.croe.validate.code.sms;

import com.imooc.security.croe.validate.code.ValidateCode;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author 袁毅雄
 * @description 断行验证码
 * @date 2018/9/30
 */
@Data
public class SmsCode extends ValidateCode {

    /**
     * @param code
     * @param expireTime 过期的时间，单位秒
     */
    public SmsCode(String code, int expireTime) {
        super(code, expireTime);
    }
}
