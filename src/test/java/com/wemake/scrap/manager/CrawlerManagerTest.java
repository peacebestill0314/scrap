package com.wemake.scrap.manager;

import com.wemake.scrap.domain.ScrapType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.UnknownHostException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * crawler manager 테스트 클래스
 */
@SpringBootTest
class CrawlerManagerTest {

    @DisplayName("유효한 url로 성공 케이스 검증")
    @ParameterizedTest
    @CsvSource({"https://peacebestill.oopy.io"})
    void 크롤링_성공(String url) throws Exception {

        //given
        String expected = "index index Search Duplicate ▫️ index 줌인터넷 블로그 스터디 zum-blog-study zum-study JPA와 영속성 컨텍스트 NoSQL : Aerospike Spring JPA Specification 컨테이너 가상화와 도커 구글 리캡차 적용하기 엘레강트 오브젝트 원칙을 적용하여 리팩토링 (1) 엘레강트 오브젝트 원칙을 적용하여 리팩토링 (2) 객체 생성, 파괴 • 객체를 만들어야 할 때와 만들지 말아야 할 때를 구분하는 법, 올바른 객체 생성 방법, 불필요한 생성을 피하는 법, 제 때 피괴됨을 보장하고 파괴 전에 수행해야 할 정리 작업을 관리하는 법 생성자 대신 정적 팩토리 메서드를 고려하자 private 생성자나 열거 타입으로 싱글턴임을 보장하자 인스턴스 생성을 막으려거든 private 생성자를 사용하자 (유틸리티) 자원을 직접 명시하지 말고 의존 객체 주입을 사용하라 객체를 적절히 파괴하자 클래스와 인터페이스 설계 • 추상화의 기본 단위인 클래스와 인터페이스 설계에 사용하는 자바의 강력한 요소들을 알아보고, 이를 활용하여 쓰기 편하고 견고하며 유연하게 개발하는 법을 알아보자 클래스와 멤버의 접근 권한을 최소화하라 public 클래스에서는 public 필드가 아닌 접근자 메서드를 사용하라 변경 가능성을 최소화하라 상속보다는 컴포지션을 사용하라 상속을 고려해 설계하고 문서화하라. 그러지 않았다면 상속을 금지하라 추상 클래스보다는 인터페이스를 우선하라 인터페이스는 구현하는 쪽을 생각해 설계하라 010368603140302049259032";

        //when
        String result = CrawlerManager.crawl(url, ScrapType.TEXT);

        //then
        assertEquals(expected, result);
    }

    @DisplayName("url에 http, https가 없는 경우 IllegalArgumentException 검증")
    @ParameterizedTest
    @CsvSource({"www.peacebestill.oopy.io"})
    void 크롤링_실패_http_https(String url) {

        //when, then
        assertThatThrownBy(() -> CrawlerManager.crawl(url, ScrapType.TEXT))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(url);
    }

    @DisplayName("접속 불가한 url인 경우 UnknownHostException 검증")
    @ParameterizedTest
    @CsvSource({"http://www.peacebestill.oopy.iox, www.peacebestill.oopy.iox"})
    void 크롤링_실패_접속불가(String url, String message) {

        //when, then
        assertThatThrownBy(() -> CrawlerManager.crawl(url, ScrapType.TEXT))
                .isInstanceOf(UnknownHostException.class)
                .hasMessageContaining(message);
    }
}
