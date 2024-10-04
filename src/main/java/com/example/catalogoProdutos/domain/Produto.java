package com.example.catalogoProdutos.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="produto")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="produto_id", nullable = false)
    private Long id;

    @Column (name = "produto_nome", nullable = false)
    private String nome;
    @Column(name="produto_status", nullable = false)
    private int status;

    @ManyToOne
    @JoinColumn(name="categoria_id", nullable=false)
    private Categoria categoria;


    @ManyToOne
    @JoinColumn(name="empresa_id", nullable=false)
    private Empresa empresa;


    @OneToOne
    @JoinColumn(name = "estoque_id")
    private Estoque estoque;



}
