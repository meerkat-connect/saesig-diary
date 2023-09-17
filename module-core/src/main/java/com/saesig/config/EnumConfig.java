package com.saesig.config;

import com.saesig.domain.adopt.AdoptStatus;
import com.saesig.domain.adopt.AdoptStopCategory;
import com.saesig.domain.faq.FaqCategory;
import com.saesig.domain.member.MemberStatus;
import com.saesig.domain.member.SignupMethod;
import com.saesig.domain.report.ReportCategory;
import com.saesig.domain.templateManage.SendCategory;
import com.saesig.domain.templateManage.SendMethod;
import com.saesig.domain.news.NewsCategory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.saesig.domain.faq.FaqCategory;
import com.saesig.domain.policy.PolicyCategory;
import com.saesig.global.enumCode.EnumMapperFactory;
import com.saesig.global.enumCode.ExampleEnum;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

@Configuration
public class EnumConfig {
    @Bean
    public EnumMapperFactory enumMapperFactory() {
        EnumMapperFactory factory = new EnumMapperFactory(new LinkedHashMap<>());

        factory.put("exampleEnum", ExampleEnum.class);
        factory.put("faqCategory", FaqCategory.class);
        factory.put("policyCategory", PolicyCategory.class);
        factory.put("sendMethod", SendMethod.class);
        factory.put("sendCategory", SendCategory.class);
        factory.put("newsCategory", NewsCategory.class);
        factory.put("memberStatus", MemberStatus.class);
        factory.put("signupMethod", SignupMethod.class);
        factory.put("adoptStatus", AdoptStatus.class);
        factory.put("reportCategry", ReportCategory.class);
        factory.put("adoptStopCategory", AdoptStopCategory.class);

        // 해당 라인에 새롭게 추가한 Enum 클래스들 추가

        return factory;
    }
}
