package com.cmc.cmc_server.infra;

import com.cmc.cmc_server.domain.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChallengeRepository extends JpaRepository<Challenge, Long> {

}
