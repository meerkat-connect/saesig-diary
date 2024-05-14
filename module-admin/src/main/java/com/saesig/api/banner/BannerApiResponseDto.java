package com.saesig.api.banner;

import com.saesig.domain.banner.ExposureLocation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BannerApiResponseDto {
    private Long id;

    private String title;

    private String url;

    private Long ord;

    private String isEnabled;

    private ExposureLocation exposureLocation;

    private String savedFileName;
}
