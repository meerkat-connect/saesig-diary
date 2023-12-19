package com.saesig.batch.job;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

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
                .next(toDormantStep2())
                .build();
    }

    @Bean
    public Step toDormantStep1() {
        // 휴면회원 대상 회원 테이블 업데이트
        return stepBuilderFactory.get("toDormantStep1")
                .<Object,Object>chunk(10)
                .reader(dormantJobReader())
                .writer(dormantJobWriter())
                .build();
    }

    @Bean
    public Step toDormantStep2() {
        // 휴면회원 대상 휴면회원 테이블 인서트
        return null;
    }

    @Bean
    public JdbcCursorItemReader<Object> dormantJobReader() {
        // 마지막 로그인일시가 1년이 지난 회원 find, order by 필수
        return new JdbcCursorItemReaderBuilder<Object>()
                .name("jdbcCursorItemReader")
                .fetchSize(10)
                .sql("SELECT id, name FROM member")
                .beanRowMapper(Object.class)
                .dataSource(dataSource)
                .build();
    }

    @Bean
    public ItemWriter<Object> dormantJobWriter() {
        /*return new JdbcBatchItemWriterBuilder<Pay>()
                .dataSource(dataSource)
                .sql("insert into pay2(amount, tx_name, tx_date_time) values (:amount, :txName, :txDateTime)")
                .beanMapped()
                .build();*/
        return list -> {
            for (Object o : list) {
                log.info("object : {} ", o);
            }
        };
    }

/*    @Bean
    public CompositeItemWriter<Product> compositeItem() {
        final CompositeItemWriter<Product> compositeItemWriter = new CompositeItemWriter<>();
        compositeItemWriter.setDelegates(Arrays.asList(updateProduct(), insertOrder())); // Writer 등록
        return compositeItemWriter;
    }

    @Bean
    public JpaItemWriter<Product> updateProduct() {
        final JpaItemWriter<Product> itemWriter = new JpaItemWriter<>();
        itemWriter.setEntityManagerFactory(entityManagerFactory);    // update product
        return itemWriter;
    }

    @Bean
    public JdbcBatchItemWriter<Product> insertOrder() {
        return new JdbcBatchItemWriterBuilder<Product>()
                .dataSource(dataSource)
                .sql("INSERT INTO orders(product_id, amount) VALUES (:id, :amount)") // insert order
                .beanMapped()
                .build();
    }*/


}
