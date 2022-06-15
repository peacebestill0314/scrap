package com.wemake.scrap.domain;

import lombok.Getter;

/**
 * 크롤링 타입 클래스
 */
@Getter
public enum ScrapType {

    HTML("html 포함 전체"),
    TEXT("html 제외 텍스트");

    private final String description;

    ScrapType(String description) {
        this.description = description;
    }

}
