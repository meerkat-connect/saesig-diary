package com.saesig.api.banner;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BannerApiService {
    private final BannerApiRepository bannerApiRepository;
    public Page<BannerApiResponseDto> findAll(BannerApiRequestDto bannerApiRequestDto, Pageable pageable) {
        return bannerApiRepository.findAll(bannerApiRequestDto, pageable);
    }
}
