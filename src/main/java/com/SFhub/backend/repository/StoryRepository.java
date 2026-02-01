package com.SFhub.backend.repository;

import com.SFhub.backend.entity.Story;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoryRepository extends JpaRepository<Story, Long> {
    // Optional: custom queries will go here later
}
