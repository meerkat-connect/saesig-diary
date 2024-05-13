package com.saesig.api.banner;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.saesig.domain.banner.QBanner;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class BannerApiRepository {
    private final JPAQueryFactory queryFactory;
    public Page<BannerApiResponseDto> findAll(BannerApiRequestDto bannerApiRequestDto, Pageable pageable) {
        QBanner banner = QBanner.banner;

        QueryResults<BannerApiResponseDto> banners = queryFactory.select(
                    Projections.fields(
                            BannerApiResponseDto.class,
                            banner.id
                            , banner.title
                            , banner.url
                            , banner.ord
                            , banner.isEnabled
                        )
                ).from(banner)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(banner.createdAt.desc())
                .fetchResults();

        return new PageImpl<>(banners.getResults(), pageable, banners.getTotal());
    }
}
