package saesigDiary.config;


import com.p6spy.engine.spy.P6SpyOptions;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class P6SpyConfig {
    @PostConstruct
    public void setMessageFormatter() {
        P6SpyOptions.getActiveInstance().setLogMessageFormat(CustomP6SpyMessageFormatter.class.getName());
    }

}
