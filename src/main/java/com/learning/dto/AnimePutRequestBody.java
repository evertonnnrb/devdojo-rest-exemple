package com.learning.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AnimePutRequestBody {

    @NotNull(message = "Anime id is required")
    private Long id;

    @NotNull(message = "Anime name is required")
    private String name;
}
