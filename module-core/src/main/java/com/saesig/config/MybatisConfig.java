package com.saesig.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.h2.tools.Server;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;
import java.net.Socket;

@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = {"com"},
        annotationClass = org.apache.ibatis.annotations.Mapper.class)
public class MybatisConfig {

    @Autowired
    ApplicationContext applicationContext;

    @Bean
    @Profile("local")
    @ConfigurationProperties("spring.datasource")
    public DataSource localDataSource() throws Exception {
        boolean result = false;

        try {
            (new Socket("127.0.0.1", 9092)).close();

            result = true;
        }
        catch(Exception e) {
        }

        if(!result) {
            Server.createTcpServer("-tcp", "-ifNotExists", "-tcpAllowOthers", "-tcpPort", "9092").start();
        }

        return new com.zaxxer.hikari.HikariDataSource();
    }

    @Bean
    @Profile("!local")
    @ConfigurationProperties("spring.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) throws IOException {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);

        factoryBean.setMapperLocations(applicationContext.getResources("classpath:mybatis/mappers/*.xml"));
        factoryBean.setConfigLocation(applicationContext.getResource("classpath:mybatis/mybatisConfig.xml"));

        return factoryBean;
    }

    @Bean
    public SqlSessionTemplate sqlSession(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
