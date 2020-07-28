package com.github.shoothzj.demo.base.module;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author AkkaJava
 * @param <T>
 */
public class BaseResponse<T> {

    private static final Logger log = LoggerFactory.getLogger(BaseResponse.class);

    T body;

    StatusEnum status;

    public BaseResponse(T body) {
        this.body = body;
        this.status = StatusEnum.SUCCESS;
    }

    public BaseResponse(StatusEnum status) {
        this.status = status;
    }

    public boolean isSucess() {
        return StatusEnum.SUCCESS.equals(status);
    }

}
