package com.ryan.library_fullstack.database.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "livros")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class LivroEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String titulo;
    @NotNull
    @Column(name = "ano_de_publicacao")
    private Integer anoPublicacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id", nullable = false)
    private AutorEntity autorEntity;
}
