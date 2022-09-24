package com.cmc.cmc_server.infra;

import com.cmc.cmc_server.domain.Mission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MissionRepository extends JpaRepository<Mission, Long> {
    List<Mission> findAll();
}
