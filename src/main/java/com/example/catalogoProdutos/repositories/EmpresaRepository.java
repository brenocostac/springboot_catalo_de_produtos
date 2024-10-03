package com.example.catalogoProdutos.repositories;

import com.example.catalogoProdutos.domain.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {
    List<Empresa> findByStatusNot(int status);
}
