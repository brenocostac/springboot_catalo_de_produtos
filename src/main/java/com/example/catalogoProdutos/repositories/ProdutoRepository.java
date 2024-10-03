package com.example.catalogoProdutos.repositories;

import com.example.catalogoProdutos.domain.Categoria;
import com.example.catalogoProdutos.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    List<Produto> findByStatusNot(int status);
}
