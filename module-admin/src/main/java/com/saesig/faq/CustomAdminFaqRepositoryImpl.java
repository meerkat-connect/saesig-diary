package com.saesig.faq;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.saesig.domain.faq.FaqCategory;
import com.saesig.domain.faq.QFaq;
import com.saesig.domain.member.QMember;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.thymeleaf.util.StringUtils;

@RequiredArgsConstructor
public class CustomAdminFaqRepositoryImpl implements CustomAdminFaqRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<FaqResponseDto> findAll(FaqRequestDto faqRequestDto) {
        QFaq qFaq = QFaq.faq;
        QMember qMember = QMember.member;

        Integer pageNum = faqRequestDto.getStart() / faqRequestDto.getLength();
        PageRequest of = PageRequest.of(pageNum, faqRequestDto.getLength());
        BooleanBuilder builder = new BooleanBuilder();

        if (!StringUtils.isEmpty(faqRequestDto.getTitle())) {
            builder.and(qFaq.title.like("%" + faqRequestDto.getTitle() + "%"));
        }

        if (!StringUtils.isEmpty(faqRequestDto.getCategory())) {
            builder.and(qFaq.category.eq(FaqCategory.from(faqRequestDto.getCategory())));
        }

        if (faqRequestDto.getIsEnabled() != null) {
            builder.and(qFaq.isEnabled.eq(faqRequestDto.getIsEnabled()));
        }

        QueryResults<FaqResponseDto> faqs = queryFactory.select(
                        Projections.fields(
                                FaqResponseDto.class,
                                qFaq.id
                                , qFaq.title
                                , qFaq.category
                                , qFaq.ord
                                , qFaq.isEnabled
                                , qFaq.createdAt
                                , qMember.nickname.as("createdBy")
                        )
                ).from(qFaq)
                .innerJoin(qMember).on(qFaq.createdBy.id.eq(qMember.id))
                .where(builder)
                .offset(of.getOffset()) //0부터 시작 (zero index)
                .limit(of.getPageSize()) // 최대 2건 조회
                .orderBy(qFaq.ord.asc())
                .fetchResults();

        return new PageImpl<>(faqs.getResults(), of, faqs.getTotal());
    }
}
