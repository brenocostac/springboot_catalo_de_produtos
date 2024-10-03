package com.example.catalogoProdutos.repositories;

import com.example.catalogoProdutos.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriaRepository  extends JpaRepository<Categoria, Integer> {
    List<Categoria> findByStatusNot(int status);
}
