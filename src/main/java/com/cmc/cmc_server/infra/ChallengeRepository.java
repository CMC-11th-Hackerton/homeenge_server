package com.cmc.cmc_server.infra;

import com.cmc.cmc_server.domain.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChallengeRepository extends JpaRepository<Challenge, Long> {

    List<Challenge> findAllByOwnerIdOrderByCountsDesc(Long id);

}
