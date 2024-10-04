package com.example.catalogoProdutos.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EstoqueResponse {

    private Long id;
    private BigDecimal precoVenda;
    private Long produtoId;
    private int quantidade;
    private String produtoNome;
}