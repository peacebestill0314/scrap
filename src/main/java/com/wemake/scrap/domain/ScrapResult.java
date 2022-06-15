package com.wemake.scrap.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

/**
 * 몫과 나머지로 이루어진 클래스
 */
@Builder
@Getter
@Setter
public class ScrapResult {

    private String quotient;
    private String remainder;

    public ScrapResult(String quotient, String remainder) {
        this.quotient = quotient;
        this.remainder = remainder;
    }

    public ScrapResult of(String quotient, String remainder) {
        return new ScrapResult(quotient, remainder);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScrapResult that = (ScrapResult) o;
        return Objects.equals(quotient, that.quotient) && Objects.equals(remainder, that.remainder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(quotient, remainder);
    }

    @Override
    public String toString() {
        return " 최종 결과 :\n " + "quotient=" + quotient + "\n remainder=" + remainder;
    }
}
