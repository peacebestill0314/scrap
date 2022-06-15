package com.wemake.scrap.domain;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * scrap 요청 클래스
 */
@Getter
@Setter
public class ScrapRequest {

    @NotNull(message = "url은 필수 값 입니다")
    private String url;

    @NotNull(message = "type은 필수 값 입니다")
    private ScrapType type;

    @Min(1)
    @NotNull(message = "unit은 필수 값 입니다")
    private Integer unit;

}
