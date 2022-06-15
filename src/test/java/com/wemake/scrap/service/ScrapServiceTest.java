package com.wemake.scrap.service;

import com.wemake.scrap.domain.ScrapRequest;
import com.wemake.scrap.domain.ScrapResult;
import com.wemake.scrap.domain.ScrapType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ScrapServiceTest {

    @Autowired
    ScrapService scrapService;

    @Test
    void HTML_스크랩_성공() throws Exception {
        //given
        ScrapRequest request = new ScrapRequest();
        request.setUrl("https://peacebestill.oopy.io");
        request.setType(ScrapType.TEXT);
        request.setUnit(10);
        ScrapResult expected = new ScrapResult("A0A0A0a0a0a0a0a1b1b1b2c2c2c2c3c3c3D3d4d4d5d6d6e8e9e9eeeeeeefgghiiiiiiiiiiiiiJJkLllllmmNnnnnnooooPPppppppppQrrrrrSSSSsssttttttuuuuuuuvvxxxyyz", "z");

        //when
        ScrapResult result = scrapService.scrap(request);

        //then
        assertEquals(expected, result);
    }

}
