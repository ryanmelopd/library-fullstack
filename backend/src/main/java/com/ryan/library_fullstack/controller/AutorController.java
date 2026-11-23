package com.ryan.library_fullstack.controller;

import com.ryan.library_fullstack.dto.request.AutorRequestDTO;
import com.ryan.library_fullstack.dto.response.AutorResponseDTO;
import com.ryan.library_fullstack.service.AutorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/autores")
@RequiredArgsConstructor
public class AutorController {
    private final AutorService autorService;

    @GetMapping
    public List<AutorResponseDTO> listar() {
        return autorService.listar();
    }

    @GetMapping("/{id}")
    public AutorResponseDTO buscarPorId(@PathVariable Long id) {
        return autorService.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AutorResponseDTO criar(@Valid @RequestBody AutorRequestDTO dto) {
        return autorService.criar(dto);
    }

    @PutMapping("/{id}")
    public AutorResponseDTO atualizar(@PathVariable Long id, @Valid @RequestBody AutorRequestDTO dto) {
        return autorService.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        autorService.deletar(id);
    }
}