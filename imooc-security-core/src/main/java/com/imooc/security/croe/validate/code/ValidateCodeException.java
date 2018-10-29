package com.imooc.security.croe.validate.code;

/**
 * @author 袁毅雄
 * @description 验证码异常
 * @date 2018/9/30
 */
public class ValidateCodeException extends org.springframework.security.core.AuthenticationException {

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
