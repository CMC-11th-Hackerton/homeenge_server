package com.cmc.cmc_server.infra;

import com.cmc.cmc_server.domain.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long> {

}
