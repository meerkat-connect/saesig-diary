package com.saesig.batch.job;

import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DormantJobConfigurationTest {
    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;


}