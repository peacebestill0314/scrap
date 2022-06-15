package com.wemake.scrap.common;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * 에러 메세지 클래스
 */
@Getter
@Setter
@Builder
public class ErrorMessage {

    private HttpStatus code;
    private String message;

    public ErrorMessage(HttpStatus code, String message) {
        this.code = code;
        this.message = message;
    }

}
