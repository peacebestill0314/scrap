package com.wemake.scrap.domain;

import com.wemake.scrap.common.Enum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * scrap 요청 클래스
 */
@Setter
@Getter
@ToString
public class ScrapRequest {

    @NotNull()
    private String url;

    @Enum(enumClass = ScrapType.class, ignoreCase = true, message = "html, text 타입만 유효합니다.")
    private String type;

    @Min(1)
    @NotNull()
    private Integer unit;

    public ScrapType getScrapType() {
        return ScrapType.valueOf(type.toUpperCase());
    }

}
