package cn.ligen.server.common.exception.handler;

import cn.ligen.server.common.exception.BadRequestException;
import cn.ligen.server.common.util.CommonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author ligen
 * @date 2023/9/1 16:44
 * @description
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 业务一场进行全局捕获
     * @return
     */
    @ExceptionHandler(value = BadRequestException.class)
    public CommonResult<Object> badException(BadRequestException e) {
        return CommonResult.failed(e.getCode(), e.getMessage());
    }
}
