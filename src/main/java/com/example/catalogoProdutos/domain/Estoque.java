package com.example.catalogoProdutos.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name="estoque")
public class Estoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="estoque_id", nullable = false)
    private Long id;

    @Column(name = "preco_compra", nullable = false)
    private BigDecimal precoCompra;

    @Column(name = "preco_venda", nullable = false)
    private BigDecimal precoVenda;


    @OneToMany(mappedBy = "estoque", cascade = CascadeType.ALL)
    private Set<Produto> produtos;
}
