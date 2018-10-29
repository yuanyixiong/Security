package com.imooc.security.croe.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author 袁毅雄
 * @description 验证码生成器
 * @date 2018/10/18
 */
public interface ValidateCodeGenertor {

    /**
     * 生成验证码
     *
     * @param request
     * @return
     */
    ValidateCode genertor(ServletWebRequest request);

}
