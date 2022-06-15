package com.wemake.scrap.domain;


import lombok.Builder;

import java.util.Objects;

/**
 * 알파벳과 숫자로 이루어진 클래스
 */
@Builder
public class Letter {

    private final String alphabet;
    private final String number;

    public Letter(String alphabet, String number) {
        this.alphabet = alphabet;
        this.number = number;
    }

    public static Letter of(String alphabet, String number) {
        return new Letter(alphabet, number);
    }

    public String alphabet() {
        return alphabet;
    }

    public String number() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Letter letter = (Letter) o;
        return Objects.equals(alphabet, letter.alphabet) && Objects.equals(number, letter.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(alphabet, number);
    }

    @Override
    public String toString() {
        return "\n 정렬 결과 :\n " + "alphabet = " + alphabet + " \n number = " + number;
    }
}
