package com.wemake.scrap.endpoint;

import com.wemake.scrap.service.ScrapService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * 엔드포인트 테스트 클래스
 */
@WebMvcTest(ScrapController.class)
class ScrapControllerTest {

    public static final String SCRAP_URL = "/scrap-url";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ScrapService scrapService;

    @DisplayName("필수값이 모두 충족되면 성공 검증")
    @ParameterizedTest
    @CsvSource({"https://peacebestill.oopy.io, html, 5", "https://front.wemakeprice.com, text, 10"})
    public void 엔드포인트_성공_검증(String url, String type, String unit) throws Exception {

        // given
        MultiValueMap<String, String> param = new LinkedMultiValueMap<>();
        param.add("url", url);
        param.add("type", type);
        param.add("unit", unit);

        //when
        final ResultActions actions = mvc.perform(get(SCRAP_URL)
                        .params(param))
                .andDo(print());

        //then
        actions.andExpect(status().isOk())
                .andDo(print());

    }

    @DisplayName("url, type, unit 값이 하나라도 누락 되면 실패 검증")
    @ParameterizedTest
    @CsvSource({"https://peacebestill.oopy.io, html, ", "https://peacebestill.oopy.io, '', 5", " , text, 10"})
    public void 엔드포인트_실패_필수값누락(String url, String type, String unit) throws Exception {

        // given
        MultiValueMap<String, String> param = new LinkedMultiValueMap<>();
        param.add("url", url);
        param.add("type", type);
        param.add("unit", unit);

        //when
        final ResultActions actions = mvc.perform(get(SCRAP_URL)
                        .params(param))
                .andDo(print());

        //then
        actions.andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print());
    }

    @DisplayName("type 값은 html, text 두가지만 유효함을 검증")
    @ParameterizedTest
    @CsvSource({"https://peacebestill.oopy.io, XXXX, 5"})
    public void 엔드포인트_실패_타입값오류(String url, String type, String unit) throws Exception {

        // given
        MultiValueMap<String, String> param = new LinkedMultiValueMap<>();
        param.add("url", url);
        param.add("type", type);
        param.add("unit", unit);

        //when
        final ResultActions actions = mvc.perform(get(SCRAP_URL)
                        .params(param))
                .andDo(print());

        //then
        actions.andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("type : html, text 타입만 유효합니다."))
                .andDo(print());

    }

}