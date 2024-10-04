package com.example.catalogoProdutos.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

    @Column(name = "estoque_status")
    private int status;
    @Column(name = "estoque_produto_qtd")
    private int quantidadeProduto;
    @JsonIgnore
    @OneToOne(mappedBy = "estoque", cascade = CascadeType.ALL)
    private Produto produto;
}
