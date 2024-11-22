package com.saesig.report;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.saesig.domain.report.Report;
import com.saesig.domain.report.ReportCategory;
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

/**
 * [클래스 요약].
 *
 * <pre>
 *
 * -
 * </pre>
 *
 * @author :
 * @ClassName :
 * @Description :
 * @date :
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */

@Import(DataConfig.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
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
                .category(ReportCategory.TYPE_A)
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
                .category(ReportCategory.TYPE_A)
                .content("content")
                .build();
        reportRepository.save(report);

        //when
        Report savedRequest = reportRepository.findById(report.getId()).get();

        //then
        assertThat(savedRequest).isNotNull();
    }

}

@TestConfiguration
@TestPropertySource(locations = "classpath:/application-test.properties")
class DataConfig {
    @PersistenceContext
    private EntityManager entityManager;

    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(entityManager);
    }
}
