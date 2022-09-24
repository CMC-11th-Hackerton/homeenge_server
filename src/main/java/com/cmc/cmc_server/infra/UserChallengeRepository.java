package com.cmc.cmc_server.infra;

import com.cmc.cmc_server.domain.Challenge;
import com.cmc.cmc_server.domain.UserChallenge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserChallengeRepository extends JpaRepository<UserChallenge, Long> {

    UserChallenge findByUser_Id(Long userId);
}
