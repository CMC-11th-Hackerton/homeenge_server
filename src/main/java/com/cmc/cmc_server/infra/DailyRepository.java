package com.cmc.cmc_server.infra;

import com.cmc.cmc_server.domain.Daily;
import com.cmc.cmc_server.domain.User;
import com.cmc.cmc_server.domain.UserChallenge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DailyRepository extends JpaRepository<Daily, Long> {
    List<Daily> findByUser(User user);
}
