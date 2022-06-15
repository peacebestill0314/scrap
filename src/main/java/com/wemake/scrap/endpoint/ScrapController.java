package com.wemake.scrap.endpoint;

import com.wemake.scrap.domain.ScrapRequest;
import com.wemake.scrap.domain.ScrapResult;
import com.wemake.scrap.service.ScrapService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * scrap 컨트롤러
 */
@RestController
@RequiredArgsConstructor
public class ScrapController {

    private final ScrapService scrapService;

    @ResponseBody
    @GetMapping("/scrap-url")
    public ResponseEntity<ScrapResult> scrap(@Valid @ModelAttribute ScrapRequest scrapRequest) throws Exception {
        return ResponseEntity.ok(scrapService.scrap(scrapRequest));
    }
}