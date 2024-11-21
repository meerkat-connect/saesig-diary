package com.saesig.report;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.saesig.domain.report.Report;
import com.saesig.domain.report.ReportCategory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

@Sql("/schema-mysql.sql")
@Import(DataConfig.class)
@DataJpaTest
class ReportQuerydslRepositoryImplTest {
    @Autowired
    private ReportRepository reportRepository;


    @Test
    @DisplayName("등록테스트")
    public void 등록테스트() {
        //given
        Report report = Report.builder()
                .category(ReportCategory.TYPE_A)
                .content("content")
                .build();

        //when
        Report save = reportRepository.save(report);
        Report saved = reportRepository.findById(save.getId()).get();

        //then
        System.out.println(report);
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
