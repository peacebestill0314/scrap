package com.wemake.scrap.service;

import com.wemake.scrap.domain.Letter;
import com.wemake.scrap.domain.ScrapRequest;
import com.wemake.scrap.domain.ScrapResult;
import com.wemake.scrap.manager.CrawlerManager;
import com.wemake.scrap.manager.ParserManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScrapService {

    /**
     * scrap 서비스
     * - crawl : url 크롤링
     * - filter : 문자열 필터링
     * - cross : 숫자, 문자 교차
     * - bind : 출력단위 묶음
     */
    public ScrapResult scrap(ScrapRequest request) throws Exception {
        String crawlResult = CrawlerManager.crawl(request.getUrl(), request.getType());
        Letter letter = ParserManager.filter(crawlResult);
        String clossResult = ParserManager.cross(letter);
        return ParserManager.bind(clossResult, request.getUnit());
    }

}
