package com.imooc.security.croe.validate.code.Image;

import com.imooc.security.croe.validate.code.AbstractValidateCodeProcessor;
import com.imooc.security.croe.validate.code.ValidateCode;
import com.imooc.security.croe.validate.code.ValidateCodeProcessor;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * @author 袁毅雄
 * @description
 * @date 2018/10/20
 */
@Service
public class ImageCodeProcessor extends AbstractValidateCodeProcessor {

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Override
    protected void save(ServletWebRequest request, ValidateCode validateCode) {
        sessionStrategy.setAttribute(request, ValidateCodeProcessor.SESSION_KEY+"_IMAGE", validateCode);
    }
    @Override
    protected void send(ServletWebRequest request, ValidateCode validateCode) throws IOException {
        ImageCode imageCode = (ImageCode) validateCode;
        ImageIO.write(imageCode.getImage(), "JPEG", request.getResponse().getOutputStream());
    }
}
