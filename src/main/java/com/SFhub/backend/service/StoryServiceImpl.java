package com.SFhub.backend.service;

import com.SFhub.backend.DTO.StoryRequestDTO;
import com.SFhub.backend.DTO.StoryResponseDTO;
import com.SFhub.backend.entity.Story;
import com.SFhub.backend.repository.StoryRepository;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;

@Service
public class StoryServiceImpl implements StoryService {

    private final StoryRepository storyRepository;

    public StoryServiceImpl(StoryRepository storyRepository) {
        this.storyRepository = storyRepository;
    }

    @Override
    public StoryResponseDTO createStory(StoryRequestDTO dto) {

        Story story = new Story();
        story.setTitle(dto.getTitle());
        story.setContent(dto.getContent());
        story.setAuthorName(dto.getAuthorName());
        story.setLicenseType(dto.getLicenseType());

        return mapToResponse(storyRepository.save(story));
    }

    @Override
    public List<StoryResponseDTO> getAllStories() {
        return storyRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public StoryResponseDTO getStoryById(Long id) {

        Story story = storyRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Story not found"));

        return mapToResponse(story);
    }

    @Override
    public StoryResponseDTO updateStory(Long id, StoryRequestDTO dto) {

        Story story = storyRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Story not found"));

        story.setTitle(dto.getTitle());
        story.setContent(dto.getContent());
        story.setAuthorName(dto.getAuthorName());
        story.setLicenseType(dto.getLicenseType());

        return mapToResponse(storyRepository.save(story));
    }

    @Override
    public void deleteStory(Long id) {

        if (!storyRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Story not found");
        }

        storyRepository.deleteById(id);
    }

    private StoryResponseDTO mapToResponse(Story story) {

        StoryResponseDTO dto = new StoryResponseDTO();

        dto.setId(story.getId());
        dto.setTitle(story.getTitle());
        dto.setContent(story.getContent());
        dto.setAuthorName(story.getAuthorName());
        dto.setLicenseType(story.getLicenseType());
        dto.setIsVerified(story.getIsVerified());
        dto.setCreatedAt(story.getCreatedAt());

        return dto;
    }
}