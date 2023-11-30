package com.saesig.common.log;

import com.saesig.domain.log.AdminAccessLog;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AdminAccessLogService {
    private final AdminAccessLogRepository adminAccessLogRepository;

    public void insertLog(AdminAccessLog adminAccessLog){
        adminAccessLogRepository.save(adminAccessLog);
    }

}
