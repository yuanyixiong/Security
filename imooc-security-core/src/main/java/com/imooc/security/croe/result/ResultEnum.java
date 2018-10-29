package com.imooc.security.croe.result;

import lombok.Getter;

/**
 * @author 袁毅雄
 * @description 结果集工具枚举
 * @date 2018/9/30
 */
@Getter
public enum ResultEnum {

    /**
     * 成功
     */
    SUCCESS(0, "接口调用成功！"),

    /**
     * 接口调用失败
     */
    FAIL(-1, "接口调用失败"),

    /**
     * 请求参数异常
     */
    BAD_REQUEST(400, "请求参数异常"),

    /**
     * 未认证成功
     */
    UN_AUTH(401, "token认证失败"),
    /**
     * 未认证成功
     */
    AUTH_OVERDUE(409, "token认证过期"),
    /**
     * 服务请求异常
     */
    SERVICE_ERROR(500, "服务请求异常");

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
