package com.example.catalogoProdutos.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.catalogoProdutos.domain.Estoque;

import jakarta.transaction.Transactional;

public interface EstoqueRepository extends JpaRepository<Estoque, Integer>{

    List<Estoque> findByStatusNot(int status);



}