package com.learning.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AnimePostRequestBody {

    @NotBlank(message = "Anime name is required")
    @NotEmpty(message = "Anime name is required")
    @NotNull(message = "Anime name is required")
    private String name;
}
