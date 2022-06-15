package com.wemake.scrap.exception;

/**
 * 크롤링 데이터 empty exception 클래스
 */
public class CrawlerEmptyException extends NullPointerException{

    private static String ERROR_MESSAGE = "크롤링 데이터가 비어있습니다.";
    public CrawlerEmptyException() {
        super(ERROR_MESSAGE);
    }
}
