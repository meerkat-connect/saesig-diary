package com.saesig.api.faq;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.saesig.domain.faq.QFaq;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

@RequiredArgsConstructor
@Repository
public class FaqApiRepository {
    private final JPAQueryFactory queryFactory;

    public Page<FaqApiResponseDto> findAll(FaqApiRequestDto faqRequestDto) {
        QFaq qFaq = QFaq.faq;
        Pageable pageable = faqRequestDto.getPageable();
        BooleanBuilder builder = new BooleanBuilder();

        if (!StringUtils.isEmpty(faqRequestDto.getKeyword())) {
            builder.and(
                    qFaq.title.like("%" + faqRequestDto.getKeyword() + "%")
                    .or(qFaq.content.like("%" + faqRequestDto.getKeyword() + "%"))
            );
        }

        QueryResults<FaqApiResponseDto> faqs = queryFactory.select(
                        Projections.fields(
                                FaqApiResponseDto.class,
                                qFaq.id
                                , qFaq.title
                                , qFaq.category
                                , qFaq.ord
                                , qFaq.isEnabled
                        )
                ).from(qFaq)
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(qFaq.ord.desc())
                .fetchResults();

        return new PageImpl<>(faqs.getResults(), pageable, faqs.getTotal());
    }
}
