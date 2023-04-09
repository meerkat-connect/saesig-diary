package saesigDiary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class SaesigDiaryApplication {
    public static void main(String[] args) {
        SpringApplication.run(SaesigDiaryApplication.class, args);
    }

}
