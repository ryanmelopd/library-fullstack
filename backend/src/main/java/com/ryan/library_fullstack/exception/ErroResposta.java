package com.ryan.library_fullstack.exception;

import java.util.Map;

public record ErroResposta(
        int status,
        String mensagem,
        Map<String, String> campos
) {}