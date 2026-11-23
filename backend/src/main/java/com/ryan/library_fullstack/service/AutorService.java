package com.ryan.library_fullstack.service;

import com.ryan.library_fullstack.database.model.AutorEntity;
import com.ryan.library_fullstack.database.repository.AutorRepository;
import com.ryan.library_fullstack.database.repository.LivroRepository;
import com.ryan.library_fullstack.dto.request.AutorRequestDTO;
import com.ryan.library_fullstack.dto.response.AutorResponseDTO;
import com.ryan.library_fullstack.exception.RecursoNaoEncontradoException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AutorService {
    private final AutorRepository autorRepository;
    private final LivroRepository livroRepository;

    @Transactional
    public List<AutorResponseDTO> listar() {
        return autorRepository.findAll().stream()
                .map(this::paraResponse)
                .toList();
    }

    @Transactional
    public AutorResponseDTO buscarPorId(Long id) {
        return paraResponse(buscarEntidade(id));
    }

    @Transactional
    public AutorResponseDTO criar(AutorRequestDTO dto) {
        AutorEntity autor = AutorEntity.builder()
                .nome(dto.nome())
                .nacionalide(dto.nacionalidade())
                .build();

        return paraResponse(autorRepository.save(autor));
    }

    @Transactional
    public AutorResponseDTO atualizar(Long id, AutorRequestDTO dto) {
        AutorEntity autor = buscarEntidade(id);
        autor.setNome(dto.nome());
        autor.setNacionalide(dto.nacionalidade());

        return paraResponse(autorRepository.save(autor));
    }

    @Transactional
    public void deletar(Long id) {
        AutorEntity autor = buscarEntidade(id);

        if (livroRepository.existsByAutorId(id)) {
            throw new IllegalStateException("Não é possível excluir um autor que possui livros");
        }

        autorRepository.delete(autor);
    }

    private AutorEntity buscarEntidade(Long id) {
        return autorRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Autor não encontrado com id " + id));
    }

    private AutorResponseDTO paraResponse(AutorEntity autor) {
        return new AutorResponseDTO(autor.getId(), autor.getNome(), autor.getNacionalide());
    }
}