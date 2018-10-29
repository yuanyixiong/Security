package com.imooc.security.croe.validate.code;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.Map;

/**
 * @author 袁毅雄
 * @description 验证码处理器，负责验证码的主流程，默认实现
 * @date 2018/10/20
 */
public abstract class AbstractValidateCodeProcessor implements ValidateCodeProcessor {

    @Autowired
    private Map<String, ValidateCodeGenertor> validateCodeGenertor;

    @Override
    public void createValidateCode(ServletWebRequest request) throws Exception {
        ValidateCode validateCode = genertor(request);
        save(request, validateCode);
        send(request, validateCode);
    }

    /**
     * 生成验证码
     *
     * @param request
     * @return
     */
    protected ValidateCode genertor(ServletWebRequest request) {
        String type = StringUtils.substringAfter(request.getRequest().getRequestURI(), "/code/");
        String classIocName = String.format("%s%s", type, "CodeGenertor");
        return validateCodeGenertor.get(classIocName).genertor(request);
    }

    /**
     * 保存验证码
     *
     * @param request
     * @param validateCode
     */
    protected abstract void save(ServletWebRequest request, ValidateCode validateCode);

    /**
     * 发送验证码
     *
     * @param request
     * @param validateCode
     */
    protected abstract void send(ServletWebRequest request, ValidateCode validateCode) throws Exception;

}
