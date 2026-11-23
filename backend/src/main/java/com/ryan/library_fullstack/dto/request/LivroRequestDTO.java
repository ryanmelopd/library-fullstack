package com.ryan.library_fullstack.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LivroRequestDTO(
        @NotBlank
        String titulo,
        Integer anoPublicacao,
        @NotNull
        Long autorId
) {}