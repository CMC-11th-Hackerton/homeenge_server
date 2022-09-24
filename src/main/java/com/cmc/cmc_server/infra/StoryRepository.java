package com.cmc.cmc_server.infra;

import com.cmc.cmc_server.domain.Story;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StoryRepository extends JpaRepository<Story, Long> {

    List<Story> findByUserIdAndChallenge_Id(long userId, long challengeId);
}
