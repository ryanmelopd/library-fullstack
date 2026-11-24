package com.ryan.library_fullstack.service;

import com.ryan.library_fullstack.database.model.AutorEntity;
import com.ryan.library_fullstack.database.model.LivroEntity;
import com.ryan.library_fullstack.database.repository.AutorRepository;
import com.ryan.library_fullstack.database.repository.LivroRepository;
import com.ryan.library_fullstack.dto.request.LivroRequestDTO;
import com.ryan.library_fullstack.dto.response.LivroResponseDTO;
import com.ryan.library_fullstack.exception.RecursoNaoEncontradoException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LivroService {
    private final LivroRepository livroRepository;
    private final AutorRepository autorRepository;

    @Transactional
    public List<LivroResponseDTO> listar(Long autorId) {
        List<LivroEntity> livros = (autorId == null)
                ? livroRepository.findAll()
                : livroRepository.findByAutorId(autorId);

        return livros.stream().map(this::paraResponse).toList();
    }

    @Transactional
    public LivroResponseDTO buscarPorId(Long id) {
        return paraResponse(buscarEntidade(id));
    }

    @Transactional
    public LivroResponseDTO criar(LivroRequestDTO dto) {
        LivroEntity livro = LivroEntity.builder()
                .titulo(dto.titulo())
                .anoPublicacao(dto.anoPublicacao())
                .autor(buscarAutor(dto.autorId()))
                .build();

        return paraResponse(livroRepository.save(livro));
    }

    @Transactional
    public LivroResponseDTO atualizar(Long id, LivroRequestDTO dto) {
        LivroEntity livro = buscarEntidade(id);
        livro.setTitulo(dto.titulo());
        livro.setAnoPublicacao(dto.anoPublicacao());
        livro.setAutor(buscarAutor(dto.autorId()));

        return paraResponse(livroRepository.save(livro));
    }

    @Transactional
    public void deletar(Long id) {
        livroRepository.delete(buscarEntidade(id));
    }

    private LivroEntity buscarEntidade(Long id) {
        return livroRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Livro não encontrado com id " + id));
    }

    private AutorEntity buscarAutor(Long autorId) {
        return autorRepository.findById(autorId)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Autor não encontrado com id " + autorId));
    }

    private LivroResponseDTO paraResponse(LivroEntity livro) {
        return new LivroResponseDTO(
                livro.getId(),
                livro.getTitulo(),
                livro.getAnoPublicacao(),
                livro.getAutor().getId(),
                livro.getAutor().getNome());
    }
}