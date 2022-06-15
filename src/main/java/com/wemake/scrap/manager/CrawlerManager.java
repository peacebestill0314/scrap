package com.wemake.scrap.manager;

import com.wemake.scrap.common.StringUtil;
import com.wemake.scrap.domain.ScrapType;
import com.wemake.scrap.exception.CrawlerEmptyException;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;

/**
 * url로 크롤링하는 클래스
 */
@Slf4j
public class CrawlerManager {

    public static String crawl(String url, ScrapType type) throws IOException {
        Connection connect = Jsoup.connect(url);
        String crawlResult = getTextByType(connect, type);
        if (StringUtil.isEmpty(crawlResult)) {
            throw new CrawlerEmptyException();
        }
        return crawlResult;
    }

    private static String getTextByType(Connection connect, ScrapType type) throws IOException {
        String crawlResult = "";
        if (ScrapType.isHtml(type)) {
            crawlResult = connect.get().html();
        }
        if (ScrapType.isText(type)) {
            crawlResult = connect.get().text();
        }
        return crawlResult;
    }

}
