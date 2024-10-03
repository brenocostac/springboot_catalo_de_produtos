package com.example.catalogoProdutos.services;

import com.example.catalogoProdutos.domain.Empresa;
import com.example.catalogoProdutos.repositories.EmpresaRepository;
import jakarta.transaction.Transactional;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpresaService {

    private final EmpresaRepository empresaRepository;

    public EmpresaService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    @Transactional
    public List<Empresa> listarTodos() {
        return empresaRepository.findByStatusNot(-1);
    }

    @Transactional
    public Optional<Empresa> findById(int id) {
        if (id <= 0) {
            return Optional.empty();
        }
        return empresaRepository.findById(id);
    }

    @Transactional
    public void adicionarEmpresa(Empresa empresa) {
        empresaRepository.save(empresa);
    }

    @Transactional
    public void updateEmpresa(int id, Empresa empresa) {
        Empresa existingEmpresa = empresaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Empresa não encontrada"));

        existingEmpresa.setNome(empresa.getNome());
        existingEmpresa.setStatus(empresa.getStatus());

        empresaRepository.save(existingEmpresa);
    }

    @Transactional
    public void removerEmpresa(int id) {
        Empresa existingEmpresa = empresaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Empresa não encontrada"));

        existingEmpresa.setStatus(-1);
        empresaRepository.save(existingEmpresa);
    }

    public boolean existsById(int id) {
        return empresaRepository.existsById(id);
    }
}
