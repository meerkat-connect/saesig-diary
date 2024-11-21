package com.saesig.report;

import com.saesig.domain.report.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report,Long>, ReportQuerydslRepository {

}
