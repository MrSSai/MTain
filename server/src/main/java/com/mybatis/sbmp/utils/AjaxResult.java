package com.mybatis.sbmp.utils;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 响应信息主体
 *
 * @author zss
 */
@Data
@NoArgsConstructor
public class AjaxResult<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 成功
     */
    public static final int SUCCESS = 200;

    /**
     * 失败
     */
    public static final int FAIL = 500;

    private int code;

    private String msg;

    private T data;

    public static <T> AjaxResult<T> success() {
        return restResult(null, SUCCESS, "操作成功");
    }

    public static <T> AjaxResult<T> success(T data) {
        return restResult(data, SUCCESS, "操作成功");
    }

    public static <T> AjaxResult<T> success(String msg) {
        return restResult(null, SUCCESS, msg);
    }

    public static <T> AjaxResult<T> success(String msg, T data) {
        return restResult(data, SUCCESS, msg);
    }

    public static <T> AjaxResult<T> fail() {
        return restResult(null, FAIL, "操作失败");
    }
    public static <T> AjaxResult<T> fail1() {
        return restResult(null, FAIL, "当前场景考点已满");
    }


    public static <T> AjaxResult<T> fail(String msg) {
        return restResult(null, FAIL, msg);
    }

    public static <T> AjaxResult<T> fail(T data) {
        return restResult(data, FAIL, "操作失败");
    }

    public static <T> AjaxResult<T> fail(String msg, T data) {
        return restResult(data, FAIL, msg);
    }

    public static <T> AjaxResult<T> fail(int code, String msg) {
        return restResult(null, code, msg);
    }

    private static <T> AjaxResult<T> restResult(T data, int code, String msg) {
        AjaxResult<T> r = new AjaxResult<>();
        r.setCode(code);
        r.setData(data);
        r.setMsg(msg);
        return r;
    }

}
