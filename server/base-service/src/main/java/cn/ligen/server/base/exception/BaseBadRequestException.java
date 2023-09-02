package cn.ligen.server.base.exception;

import cn.ligen.server.common.exception.BadRequestException;

/**
 * @author ligen
 * @date 2023/9/1 16:38
 * @description
 */
public class BaseBadRequestException extends BadRequestException {

    public BaseBadRequestException(String message) {
        super(message);
    }
}
