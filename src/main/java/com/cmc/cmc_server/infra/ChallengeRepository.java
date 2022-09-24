package com.cmc.cmc_server.infra;

import com.cmc.cmc_server.domain.Challenge;
import com.cmc.cmc_server.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChallengeRepository extends JpaRepository<Challenge, Long> {

    List<Challenge> findTop5ByOwnerIdOrderByCountsDesc(Long id);

    List<Challenge> findAllByStep(Integer step);

    List<Challenge> findAllByOwner(User user);

    List<Challenge> findAllByOwnerAndFinishedIsFalse(User user);

    List<Challenge> findAllByOwnerAndFinishedIsTrue(User user);

}
