package com.imooc.security.croe.properties.validate.code;

import lombok.Data;

/**
 * @author 袁毅雄
 * @description 自定义属性配置-图形验证码配置
 * @date 2018/10/18
 */
@Data
public class ImageCodeProperties {

    /**
     * 验证码宽度
     */
    Integer width = 65;

    /**
     * 验证码高度
     */
    Integer height = 20;

    /**
     * 验证码数字位数
     */
    Integer length = 4;

    /**
     * 验证码失效时间
     */
    Integer expireTime = 60;

    /**
     * 请求接口
     */
    String url;
}
