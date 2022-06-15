package com.wemake.scrap.common;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * 응답 결과 클래스
 */
@Getter
@Setter
public class Result<T> {

    private HttpStatus code;
    private String message;
    private T data;

    public Result(HttpStatus code, T data) {
        this.code = code;
        this.data = data;
    }

    public Result(HttpStatus code, String message) {
        this.code = code;
        this.message = message;
    }

}
