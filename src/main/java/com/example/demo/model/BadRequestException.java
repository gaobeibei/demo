package com.example.demo.model;

import com.example.demo.enums.ResultEnum;
import lombok.Data;

@Data
public class BadRequestException extends RuntimeException {
    private Integer code;

    public BadRequestException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }
}
