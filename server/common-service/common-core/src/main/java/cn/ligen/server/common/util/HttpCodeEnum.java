package cn.ligen.server.common.util;

/**
 * @author ligen
 * @date 2023/8/26 11:16
 * @description
 */
public enum HttpCodeEnum {

    HTTP_SUCCESS(200, "请求成功"),
    HTTP_FAILED(500, "服务器错误");

    private Integer code;
    private String message;
    private HttpCodeEnum() {}
    private HttpCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}
