package com.imooc.security.croe.result;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 袁毅雄
 * @description 结果集工具类
 * @date 2018/9/30
 **/
public class ResultUtil {


    /**
     * 获取返回值为Map的Result
     *
     * @param key   key
     * @param value value
     * @return Result 对象
     */
    public static Result resultMap(String key, Object value) {
        Map<String, Object> map = new HashMap<>(16);
        map.put(key, value);
        return success(map);
    }

    /**
     * 成功或者失败
     *
     * @return result对象
     */
    public static Result successOrFail(boolean result) {
        //根据返回的状态得到result对象
        return result ? success() : fail();
    }

    /**
     * 成功
     *
     * @param object 传递的数据对象
     * @return result
     */
    public static Result success(Object object) {
        Result result = new Result();
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMsg(ResultEnum.SUCCESS.getMessage());
        result.setData(object);
        return result;
    }

    /**
     * 成功
     *
     * @param message 传递的数据对象
     * @param object  传递的数据对象
     * @return result
     */
    public static Result successMessage(String message, Object object) {
        Result result = new Result();
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMsg(message);
        result.setData(object);
        return result;
    }

    /**
     * 成功
     *
     * @return result success
     */
    public static Result success() {
        return success(null);
    }

    /**
     * 失败
     *
     * @param code 失败错误码
     * @param msg  失败的消息
     * @return result
     */
    public static Result fail(int code, String msg) {
        return new Result(code, msg);
    }

    /**
     * 失败
     * code =fail code
     * msg 需要自定义
     *
     * @param msg 错误吗
     * @return result
     */
    public static Result fail(String msg) {
        return new Result(ResultEnum.FAIL.getCode(), msg);
    }

    /**
     * 提示接口调用失败的方法
     *
     * @param resultEnum 枚举对象
     * @return result
     */
    public static Result fail(ResultEnum resultEnum) {

        return fail(resultEnum.getCode(), resultEnum.getMessage());
    }

    /**
     * 不需要知道状态情况下调用此方法
     *
     * @return new Result(-1,"接口调用失败")
     */
    public static Result fail() {
        return fail(ResultEnum.FAIL.getCode(), ResultEnum.FAIL.getMessage());
    }

    /**
     * 判断是否返回成功
     */
    public static boolean isReturnSuccess(Result result) {
        return null != result && 0 == result.getCode();
    }
}
