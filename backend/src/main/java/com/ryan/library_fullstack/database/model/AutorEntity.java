package com.ryan.library_fullstack.database.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "autores")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AutorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NotBlank
    String nome;
    String nacionalide;

    @OneToMany(mappedBy = "autor")
    private List<LivroEntity> livros = new ArrayList<>();
}
