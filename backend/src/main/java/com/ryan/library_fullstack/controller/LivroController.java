package com.ryan.library_fullstack.controller;

import com.ryan.library_fullstack.dto.request.LivroRequestDTO;
import com.ryan.library_fullstack.dto.response.LivroResponseDTO;
import com.ryan.library_fullstack.service.LivroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
@RequiredArgsConstructor
public class LivroController {
    private final LivroService livroService;

    @GetMapping
    public List<LivroResponseDTO> listar(@RequestParam(required = false) Long autorId) {
        return livroService.listar(autorId);
    }

    @GetMapping("/{id}")
    public LivroResponseDTO buscarPorId(@PathVariable Long id) {
        return livroService.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LivroResponseDTO criar(@Valid @RequestBody LivroRequestDTO dto) {
        return livroService.criar(dto);
    }

    @PutMapping("/{id}")
    public LivroResponseDTO atualizar(@PathVariable Long id, @Valid @RequestBody LivroRequestDTO dto) {
        return livroService.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        livroService.deletar(id);
    }
}