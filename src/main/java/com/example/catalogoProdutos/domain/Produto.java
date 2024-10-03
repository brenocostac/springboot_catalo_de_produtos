package com.example.catalogoProdutos.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name="produtos")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="produto_id", nullable = false)
    private Long id;

    @Column(name="produto_status", nullable = false)
    private int status;

    @OneToMany
    @JoinColumn(name="categoria_id", nullable=false)
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name="empresa_id", nullable=false)
    private Empresa empresa;

    @OneToMany
    @JoinColumn(name="estoque_id")
    private Estoque estoque;



}
