package cn.ligen.server.common.util;

/**
 * @author ligen
 * @date 2023/8/26 11:17
 * @description
 */
public class CommonResult<T> {

    private Integer code;
    private String message;
    private T data;

    private CommonResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
    private CommonResult(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> CommonResult<T> success() {
        return new CommonResult<T>(HttpCodeEnum.HTTP_SUCCESS.getCode(), HttpCodeEnum.HTTP_SUCCESS.getMessage());
    }

    public static <T> CommonResult<T> success(String message) {
        return new CommonResult<T>(HttpCodeEnum.HTTP_SUCCESS.getCode(), message);
    }

    public static <T> CommonResult<T> success(String message, T data) {
        return new CommonResult<T>(HttpCodeEnum.HTTP_SUCCESS.getCode(), message, data);
    }

    public static <T> CommonResult<T> success(T data) {
        return new CommonResult<T>(HttpCodeEnum.HTTP_SUCCESS.getCode(), HttpCodeEnum.HTTP_SUCCESS.getMessage(), data);
    }

    public static <T> CommonResult<T> failed() {
        return new CommonResult<T>(HttpCodeEnum.HTTP_REQUEST_FAILED.getCode(), HttpCodeEnum.HTTP_SUCCESS.getMessage());
    }

    public static <T> CommonResult<T> failed(String message) {
        return new CommonResult<T>(HttpCodeEnum.HTTP_REQUEST_FAILED.getCode(), message);
    }

    public static <T> CommonResult<T> failed(String message, T data) {
        return new CommonResult<T>(HttpCodeEnum.HTTP_REQUEST_FAILED.getCode(), message, data);
    }

    public static <T> CommonResult<T> failed(Integer code, String message) {
        return new CommonResult<T>(code, message);
    }

    public static <T> CommonResult<T> failed(T data) {
        return new CommonResult<T>(HttpCodeEnum.HTTP_REQUEST_FAILED.getCode(), HttpCodeEnum.HTTP_REQUEST_FAILED.getMessage(), data);
    }

    public static <T> CommonResult<T> common(Integer code, String message, T data) {
        return new CommonResult<T>(code, message, data);
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}
