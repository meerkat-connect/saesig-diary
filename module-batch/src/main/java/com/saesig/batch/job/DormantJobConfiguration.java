package com.saesig.batch.job;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.batch.item.support.CompositeItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Arrays;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class DormantJobConfiguration {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final DataSource dataSource;

    @Bean
    public Job toDormantJob() {
        // 1. 마지막 로그인일시가 1년이 지난 회원 조회 JdbcCursorItemReader
        // 2. 휴면회원 대상 회원 테이블 업데이트 JdbcCursorItemWriter
        // 3. 휴면회원 대상 휴면회원 테이블 인서트
        return jobBuilderFactory.get("toDormantJob")
                .start(toDormantStep1())
                .build();
    }

    @Bean
    public Step toDormantStep1() {
        // 휴면회원 대상 회원 테이블 업데이트
        return stepBuilderFactory.get("toDormantStep1")
                .<Member, Member>chunk(10)
                .reader(dormantJobReader())
                .writer(compositeItemWriter())
                .build();
    }

    @Bean
    public JdbcCursorItemReader<Member> dormantJobReader() {
        // 마지막 로그인일시가 1년이 지난 회원 조회, PagingItemReader 사용시에는 order by 필수
        return new JdbcCursorItemReaderBuilder<Member>()
                .name("jdbcCursorItemReader")
                .fetchSize(10)
                .sql("SELECT id, email FROM member WHERE CURRENT_TIMESTAMP - INTERVAL '1' YEAR  <= last_logged_at")
                .beanRowMapper(Member.class)
                .dataSource(dataSource)
                .build();
    }

    @Bean
    public ItemWriter<Member> updateMemberItemWriter() {
        return new JdbcBatchItemWriterBuilder<Member>()
                .dataSource(dataSource)
                .sql("UPDATE member SET status='dormant' WHERE email = :email")
                .beanMapped()
                .build();
    }

    @Bean
    public ItemWriter<Member> insertDormantMemberItemWriter() {
        return new JdbcBatchItemWriterBuilder<Member>()
                .dataSource(dataSource)
                .sql("INSERT INTO dormant_member(status) VALUES(:status) WHERE id = :email")
                .beanMapped()
                .build();
    }

    @Bean
    public CompositeItemWriter<Member> compositeItemWriter() {
        CompositeItemWriter<Member> compositeItemWriter = new CompositeItemWriter<>();
        compositeItemWriter.setDelegates(Arrays.asList(updateMemberItemWriter(), insertDormantMemberItemWriter()));
        return compositeItemWriter;
    }
}
