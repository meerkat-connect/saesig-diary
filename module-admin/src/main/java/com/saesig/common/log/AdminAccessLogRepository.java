package com.saesig.common.log;

import com.saesig.domain.log.AdminAccessLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminAccessLogRepository extends JpaRepository<AdminAccessLog, Long> {
}
