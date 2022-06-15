package com.wemake.scrap.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 크롤링 타입 클래스
 */
@Getter
@RequiredArgsConstructor
public enum ScrapType {

    HTML("html", "html 포함한 전체"),
    TEXT("text", "html 제외한 text");

    private final String value;
    private final String description;

    public static boolean isHtml(ScrapType type) {
        return type == HTML;
    }

    public static boolean isText(ScrapType type) {
        return type == TEXT;
    }

}
