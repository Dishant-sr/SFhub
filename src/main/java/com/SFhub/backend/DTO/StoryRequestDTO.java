package com.SFhub.backend.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class StoryRequestDTO {

    @NotBlank(message = "Title cannot be empty")
    @Size(min = 3, max = 100)
    private String title;

    @NotBlank(message = "Content cannot be empty")
    @Size(min = 10)
    private String content;

    @NotBlank(message = "Author name is required")
    private String authorName;

    @NotBlank(message = "License type is required")
    private String licenseType;

    // GETTERS & SETTERS

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getAuthorName() { return authorName; }
    public void setAuthorName(String authorName) { this.authorName = authorName; }

    public String getLicenseType() { return licenseType; }
    public void setLicenseType(String licenseType) { this.licenseType = licenseType; }
}
