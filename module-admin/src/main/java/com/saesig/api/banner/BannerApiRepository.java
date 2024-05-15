package com.saesig.api.banner;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.saesig.domain.banner.ExposureLocation;
import com.saesig.domain.banner.QBanner;
import com.saesig.domain.file.QFile;
import com.saesig.domain.file.QFileGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

@RequiredArgsConstructor
@Repository
public class BannerApiRepository {
    private final JPAQueryFactory queryFactory;
    public Page<BannerApiResponseDto> findAll(BannerApiRequestDto bannerApiRequestDto, Pageable pageable) {
        QBanner banner = QBanner.banner;
        QFileGroup fileGroup = QFileGroup.fileGroup;
        QFile file = QFile.file;

        BooleanBuilder builder = new BooleanBuilder();

        if (!StringUtils.isEmpty(bannerApiRequestDto.getExposureLocation())) {
            builder.and(
                    banner.exposureLocation.eq(ExposureLocation.valueOf(bannerApiRequestDto.getExposureLocation()))
            );
        }

        QueryResults<BannerApiResponseDto> banners = queryFactory.select(
                    Projections.fields(
                            BannerApiResponseDto.class,
                            banner.id
                            , banner.title
                            , banner.url
                            , banner.ord
                            , banner.isEnabled
                            , banner.exposureLocation
                            , file.savedName.as("savedFileName")
                        )
                ).from(banner)
                .innerJoin(fileGroup).on(banner.imageFileGroupid.eq(fileGroup.id))
                .innerJoin(file).on(file.fileGroup.id.eq(fileGroup.id))
                .where(banner.isEnabled.eq("Y").and(builder))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(banner.id.asc())
                .fetchResults();

        return new PageImpl<>(banners.getResults(), pageable, banners.getTotal());
    }
}
