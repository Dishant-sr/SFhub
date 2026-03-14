package com.SFhub.backend.service;

import com.SFhub.backend.DTO.StoryRequestDTO;
import com.SFhub.backend.DTO.StoryResponseDTO;

import java.util.List;

public interface StoryService {

    StoryResponseDTO createStory(StoryRequestDTO dto);

    List<StoryResponseDTO> getAllStories();

    StoryResponseDTO getStoryById(Long id);

    StoryResponseDTO updateStory(Long id, StoryRequestDTO dto);

    void deleteStory(Long id);
}