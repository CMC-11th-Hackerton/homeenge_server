package com.cmc.cmc_server.infra;

import com.cmc.cmc_server.domain.Story;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoryRepository extends JpaRepository<Story, Long> {

}
