package cn.ligen.server.common.exception;

import cn.ligen.server.common.util.HttpCodeEnum;

/**
 * @author ligen
 * @date 2023/9/1 16:18
 * @description 统一异常处理基类
 */
public class BadRequestException extends RuntimeException{

    protected Integer code;
    protected String message;

    public BadRequestException() {super();}
    public BadRequestException(String message) {
        super(message);
        this.code = HttpCodeEnum.HTTP_RESPONSE_FAILED.getCode();
        this.message = message;
    }

    public BadRequestException(HttpCodeEnum httpCodeEnum) {
        super(httpCodeEnum.getMessage());
        this.code = httpCodeEnum.getCode();
        this.message = HttpCodeEnum.HTTP_REQUEST_FAILED.getMessage();
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
        this.code = HttpCodeEnum.HTTP_REQUEST_FAILED.getCode();
    }

    public Integer getCode() { return code; }
    public String getMessage() { return message; }

}
