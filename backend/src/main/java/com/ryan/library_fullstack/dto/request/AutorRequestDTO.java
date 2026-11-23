package com.ryan.library_fullstack.dto.request;

import jakarta.validation.constraints.NotBlank;

public record AutorRequestDTO(
        @NotBlank
        String nome,
        String nacionalidade
) {}