package com.imooc.security.croe.validate.code.sms;

import com.imooc.security.croe.validate.code.AbstractValidateCodeProcessor;
import com.imooc.security.croe.validate.code.ValidateCode;
import com.imooc.security.croe.validate.code.ValidateCodeProcessor;
import com.imooc.security.croe.validate.code.sms.sender.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author 袁毅雄
 * @description
 * @date 2018/10/20
 */
@Service
public class SmsCodeProcessor extends AbstractValidateCodeProcessor {

    @Autowired
    private SmsCodeSender smsCodeSender;

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Override
    protected void save(ServletWebRequest request, ValidateCode validateCode) {
        sessionStrategy.setAttribute(request, ValidateCodeProcessor.SESSION_KEY+"_SMS", validateCode);
    }

    @Override
    protected void send(ServletWebRequest request, ValidateCode validateCode) throws ServletRequestBindingException {
        String mobile = ServletRequestUtils.getRequiredStringParameter(request.getRequest(), "mobile");
        smsCodeSender.send(mobile,validateCode.getCode());
    }
}
