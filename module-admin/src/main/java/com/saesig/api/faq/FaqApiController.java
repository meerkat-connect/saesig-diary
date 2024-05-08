package com.saesig.api.faq;

import com.saesig.api.util.Constants;
import com.saesig.error.ApiRequestResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(Constants.CONTEXT_PATH)
public class FaqApiController {
    private final FaqApiService faqApiService;

    @GetMapping("/faq")
    public ResponseEntity<ApiRequestResult> findAll(@ModelAttribute FaqApiRequestDto faqApiRequestDto, Pageable pageable) {
        Map<String, Object> result = new HashMap<>();

        Page<FaqApiResponseDto> find = faqApiService.findAll(faqApiRequestDto, pageable);

        result.put("faqs", find.getContent());
        return ResponseEntity
                .ok()
                .body(ApiRequestResult.of(result));
    }

}
