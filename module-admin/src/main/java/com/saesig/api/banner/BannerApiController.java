package com.saesig.api.banner;

import com.saesig.api.util.Constants;
import com.saesig.error.ApiRequestResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping(Constants.CONTEXT_PATH)
public class BannerApiController {
    private final BannerApiService bannerApiService;

    @GetMapping("/banner")
    public ResponseEntity<ApiRequestResult> findAll(@ModelAttribute BannerApiRequestDto bannerApiRequestDto, Pageable pageable) {
        Map<String, Object> result = new HashMap<>();

        Page<BannerApiResponseDto> find = bannerApiService.findAll(bannerApiRequestDto, pageable);

        result.put("banners", find.getContent());
        return ResponseEntity
                .ok()
                .body(ApiRequestResult.of(result));
    }
}
