package com.wemake.scrap.common;

import org.springframework.http.HttpStatus;

import java.util.Objects;

/**
 * 응답 결과를 생성하는 팩토리 클래스
 */
public class ResultFactory {

    private static final ResultFactory instance = new ResultFactory();

    private ResultFactory() {
        if (Objects.nonNull(instance)) {
            throw new RuntimeException();
        }
    }

    public static <T> Result<T> success(T t) {
        return instance.create(t);
    }

    public static <T> Result<T> fail(String message) {
        return instance.create(message);
    }

    private <T> Result<T> create(T t) {
        return new Result(HttpStatus.OK, t);
    }

    private <T> Result<T> create(String message) {
        return new Result(HttpStatus.INTERNAL_SERVER_ERROR, message);
    }
}
