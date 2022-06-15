package com.wemake.scrap.endpoint;

import com.wemake.scrap.common.Result;
import com.wemake.scrap.common.ResultFactory;
import com.wemake.scrap.domain.ScrapRequest;
import com.wemake.scrap.service.ScrapService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * scrap 컨트롤러
 * - crawl : url 크롤링
 * - filter : 문자열 필터링
 * - cross : 숫자, 문자 교차
 * - bind : 출력단위 묶음
 */
@RestController
@RequiredArgsConstructor
public class ScrapController {

    private final ScrapService scrapService;

    @ResponseBody
    @GetMapping("/scrap-url")
    public Result scrap(@Valid @ModelAttribute ScrapRequest scrapRequest) throws Exception {
        return ResultFactory.success(scrapService.scrap(scrapRequest));
    }
}