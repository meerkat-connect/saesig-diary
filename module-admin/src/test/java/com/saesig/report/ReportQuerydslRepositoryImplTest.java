package com.saesig.report;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.saesig.domain.adopt.Adopt;
import com.saesig.domain.report.Report;
import com.saesig.domain.report.ReportCategory;
import com.saesig.domain.report.ReportTargetType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.assertj.core.api.Assertions.assertThat;

@Import(DataConfig.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestPropertySource(locations = "classpath:/application-test.properties")
@DataJpaTest
class ReportQuerydslRepositoryImplTest {
    @Autowired
    private ReportRepository reportRepository;

    @BeforeAll
    public void setUp() {
    }

    @Test
    @DisplayName("등록테스트")
    public void 등록테스트() {
        //given
        Report report = Report.builder()
                .category(ReportCategory.CATEGORY_A)
                .content("content")
                .build();

        //when
        Report savedRequest = reportRepository.save(report);

        //then
        assertThat(savedRequest.getId()).isNotNull();
    }

    @Test
    @DisplayName("조회테스트")
    public void 조회테스트(){
        //given
        Report report = Report.builder()
                .category(ReportCategory.CATEGORY_A)
                .content("content")
                .build();
        reportRepository.save(report);

        //when
        Report savedRequest = reportRepository.findById(report.getId()).get();

        //then
        assertThat(savedRequest).isNotNull();
    }

    @Test
    @DisplayName("분양 게시글 조회")
    public void 분양_게시글_조회(){
        //given


        Report report = Report.builder().category(ReportCategory.CATEGORY_A)
                .targetId(1L)
                .targetType(ReportTargetType.ADOPT)
                .build();

        //when

        //then
    }

}

@TestConfiguration
class DataConfig {
    @PersistenceContext
    private EntityManager entityManager;

    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(entityManager);
    }
}
