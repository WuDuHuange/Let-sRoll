package com.wudu.Roll.util;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 该类封装了后端返回给前端的数据：包括状态码、状态消息、数据
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResult<T> {
    /**
     * 后端返回给前端的响应状态码
     */
    private int code;
    /**
     * 后端返回给前端的响应消息
     */
    private String msg;
    /**
     * 后端返回给前端的数据，类型不确定
     */
    private T data;

    /**
     * 后端返回给前端的响应成功方法
     * @param data 后端返回给前端的数据
     * @param <T> 泛型
     * @return
     */
    public static <T> CommonResult success(T data) {
        return new CommonResult(ResponseCode.SUCCESS,ResponseMessage.SUCCESS_MSG,data);
    }

    /**
     * 当处理请求失败调用此方法将失败的信息返回给前端
     * @param data 后端返回给前端的失败信息
     * @param <T>
     * @return
     */
    public static<T> CommonResult fail(T data) {
        return new CommonResult(ResponseCode.FAIL,ResponseMessage.FAIL_MSG,data);
    }
}