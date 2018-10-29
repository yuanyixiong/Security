package com.imooc.security.croe.validate.code.sms;

import com.imooc.security.croe.properties.SecurityProperties;
import com.imooc.security.croe.validate.code.ValidateCode;
import com.imooc.security.croe.validate.code.ValidateCodeGenertor;
import lombok.Data;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.Random;

/**
 * @author 袁毅雄
 * @description 短信验证码生成逻辑
 * @date 2018/10/18
 */
@Data
public class SmsCodeGenertor implements ValidateCodeGenertor {

    private SecurityProperties securityProperties;

    @Override
    public ValidateCode genertor(ServletWebRequest request) {
        int length = ServletRequestUtils.getIntParameter(request.getRequest(), "length", securityProperties.getValidateCode().getSmsCode().getLength());
        int expireTime = ServletRequestUtils.getIntParameter(request.getRequest(), "expireTime", securityProperties.getValidateCode().getSmsCode().getExpireTime());
        // 生成随机类
        Random random = new Random();
        // 取随机产生的认证码(4位数字)
        String sRand = "";
        for (int i = 0; i < length; i++) {
            String rand = String.valueOf(random.nextInt(10));
            sRand += rand;
        }
        return new SmsCode(sRand, expireTime);
    }

}
