package com.ryan.library_fullstack.dto.response;

public record LivroResponseDTO(
        Long id,
        String titulo,
        Integer anoPublicacao,
        Long autorId,
        String autorNome
) {}