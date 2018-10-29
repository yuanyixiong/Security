package com.imooc.security.croe.validate;

import com.imooc.security.croe.validate.code.ValidateCodeProcessor;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author 袁毅雄
 * @description 生成图形验证码的Controller
 * @date 2018/9/30
 */
@RestController
public class ValidateCodeController {

    @Autowired
    private Map<String,ValidateCodeProcessor> validateCodeProcessor;

    @GetMapping("/code/{type}")
    public void createCode(HttpServletRequest request,
                           HttpServletResponse response,
                           @PathVariable("type") @NotBlank String type) throws Exception {
        String classIocName = String.format("%s%s", type, "CodeProcessor");
        validateCodeProcessor.get(classIocName).createValidateCode(new ServletWebRequest(request,response));
    }
}
