package com.imooc.security.croe.validate.code;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author 袁毅雄
 * @description 验证码
 * @date 2018/10/20
 */
@Data
public class ValidateCode {

    private String code;

    private LocalDateTime expireTime;

    /**
     * @param code
     * @param expireTime 过期的时间，单位秒
     */
    public ValidateCode(String code, int expireTime) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireTime);
    }

    public boolean isExpireTime() {
        return LocalDateTime.now().isAfter(expireTime);
    }
}
