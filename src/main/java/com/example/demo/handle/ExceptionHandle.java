package com.example.demo.handle;

import com.example.demo.model.BadRequestException;
import com.example.demo.model.Result;
import com.example.demo.utils.ResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandle {

    Logger log = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e) {
        if (e instanceof BadRequestException) {
            BadRequestException badRequestException = (BadRequestException) e;
            return ResultUtils.error(badRequestException.getCode(), badRequestException.getMessage());
        } else {
            log.error(e.getMessage(), e);
            return ResultUtils.error(400, e.getMessage());
        }
    }
}
