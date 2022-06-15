package com.wemake.scrap.manager;

import com.wemake.scrap.common.StringUtil;
import com.wemake.scrap.domain.ScrapType;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;

/**
 * url로 크롤링하는 클래스
 */
@Slf4j
public class CrawlerManager {

    public static String crawl(String url, ScrapType type) throws Exception {
        Connection connect = Jsoup.connect(url);
        String crawlResult = getTextByType(connect, type);
        if (StringUtil.isEmpty(crawlResult)) {
            throw new Exception();
        }
        log.debug("\n 크롤링 결과 : \n {}", crawlResult);
        return crawlResult;
    }

    private static String getTextByType(Connection connect, ScrapType type) throws IOException {
        String crawlResult = "";
        switch (type) {
            case HTML:
                crawlResult = connect.get().html();
                break;
            case TEXT:
                crawlResult = connect.get().text();
                break;
        }
        return crawlResult;
    }

}
