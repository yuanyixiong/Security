package com.imooc.security.croe.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author 袁毅雄
 * @description 验证码处理器，负责验证码的主流程，封装不同的验证码处理逻辑
 * @date 2018/10/20
 */
public interface ValidateCodeProcessor {

    /**
     * 验证码Session 中的前缀
     */
    String SESSION_KEY = "SESSION_KEY";

    /**
     * 生成验证码
     *
     * @param request
     * @throws Exception
     */
    void createValidateCode(ServletWebRequest request) throws Exception;
}
