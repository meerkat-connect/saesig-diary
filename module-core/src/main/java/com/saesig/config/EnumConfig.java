package com.saesig.config;

import com.saesig.domain.adopt.AdoptStatus;
import com.saesig.domain.adopt.AdoptStopCategory;
import com.saesig.domain.banner.ExposureLocation;
import com.saesig.domain.board.BoardCategory;
import com.saesig.domain.diary.DiaryCategory;
import com.saesig.domain.diary.DiaryStatus;
import com.saesig.domain.diary.WeatherCategory;
import com.saesig.domain.faq.FaqCategory;
import com.saesig.domain.inquiry.InquiryCategory;
import com.saesig.domain.inquiry.InquiryStatus;
import com.saesig.domain.member.MemberStatus;
import com.saesig.domain.member.SignupMethod;
import com.saesig.domain.news.NewsCategory;
import com.saesig.domain.policy.PolicyCategory;
import com.saesig.domain.popupManage.ButtonOption;
import com.saesig.domain.report.ReportCategory;
import com.saesig.domain.role.ResourceType;
import com.saesig.domain.templateManage.SendCategory;
import com.saesig.domain.templateManage.SendMethod;
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
        factory.put("inquiryCategory", InquiryCategory.class);
        factory.put("inquiryStatus", InquiryStatus.class);
        factory.put("policyCategory", PolicyCategory.class);
        factory.put("sendMethod", SendMethod.class);
        factory.put("sendCategory", SendCategory.class);
        factory.put("newsCategory", NewsCategory.class);
        factory.put("memberStatus", MemberStatus.class);
        factory.put("signupMethod", SignupMethod.class);
        factory.put("adoptStatus", AdoptStatus.class);
        factory.put("reportCategry", ReportCategory.class);
        factory.put("adoptStopCategory", AdoptStopCategory.class);
        factory.put("exposureLocation", ExposureLocation.class);
        factory.put("buttonOption", ButtonOption.class);
        factory.put("resourceType", ResourceType.class);
        factory.put("diaryCategory", DiaryCategory.class);
        factory.put("diaryStatus", DiaryStatus.class);
        factory.put("weatherCategory", WeatherCategory.class);
        factory.put("boardCategory", BoardCategory.class);

        // 해당 라인에 새롭게 추가한 Enum 클래스들 추가

        return factory;
    }
}
