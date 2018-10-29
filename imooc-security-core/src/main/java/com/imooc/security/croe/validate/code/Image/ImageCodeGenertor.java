package com.imooc.security.croe.validate.code.Image;

import com.imooc.security.croe.properties.SecurityProperties;
import com.imooc.security.croe.validate.code.ValidateCode;
import com.imooc.security.croe.validate.code.ValidateCodeGenertor;
import lombok.Data;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @author 袁毅雄
 * @description 图形验证码生成逻辑
 * @date 2018/10/18
 */
@Data
public class ImageCodeGenertor implements ValidateCodeGenertor {

    private SecurityProperties securityProperties;

    @Override
    public ValidateCode genertor(ServletWebRequest request) {

        //获取请求中的参数，请求中没有获取应用中的值
        int width = ServletRequestUtils.getIntParameter(request.getRequest(), "width", securityProperties.getValidateCode().getImageCode().getWidth());
        int height = ServletRequestUtils.getIntParameter(request.getRequest(), "height", securityProperties.getValidateCode().getImageCode().getHeight());
        int length = ServletRequestUtils.getIntParameter(request.getRequest(), "length", securityProperties.getValidateCode().getImageCode().getLength());
        int expireTime = ServletRequestUtils.getIntParameter(request.getRequest(), "expireTime", securityProperties.getValidateCode().getImageCode().getExpireTime());

        // 在内存中创建图象
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // 获取图形上下文
        Graphics g = image.getGraphics();
        // 生成随机类
        Random random = new Random();
        // 设定背景色
        g.setColor(getRandColor(230, 255));
        g.fillRect(0, 0, width, height);
        // 设定字体
        g.setFont(new Font("Arial", Font.CENTER_BASELINE | Font.ITALIC, 18));
        // 产生0条干扰线，
        g.drawLine(0, 0, 0, 0);


        // 取随机产生的认证码(4位数字)
        String sRand = "";
        for (int i = 0; i < length; i++) {
            String rand = String.valueOf(random.nextInt(10));
            sRand += rand;
            // 将认证码显示到图象中
            // 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
            g.setColor(getRandColor(100, 150));
            g.drawString(rand, 15 * i + 6, 16);
        }
        //随机干扰线
        for (int i = 0; i < (random.nextInt(5) + 5); i++) {
            g.setColor(new Color(random.nextInt(255) + 1, random.nextInt(255) + 1, random.nextInt(255) + 1));
            g.drawLine(random.nextInt(width), random.nextInt(height), random.nextInt(width), random.nextInt(height));
        }

        g.dispose();

        return new ImageCode(image, sRand, expireTime);
    }

    /**
     * 给定范围获得随机颜色
     *
     * @param fc
     * @param bc
     * @return
     */
    private Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }
}
