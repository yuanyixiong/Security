package com.imooc.security.croe.validate.code.Image;

import com.imooc.security.croe.validate.code.ValidateCode;
import lombok.Data;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * @author 袁毅雄
 * @description 图形验证码
 * @date 2018/9/30
 */
@Data
public class ImageCode extends ValidateCode {

    private BufferedImage image;

    /**
     * @param image
     * @param code
     * @param expireTime 过期的时间，单位秒
     */
    public ImageCode(BufferedImage image, String code, int expireTime) {
        super(code, expireTime);
        this.image = image;
    }
}
