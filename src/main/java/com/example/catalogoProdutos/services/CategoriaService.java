package com.example.catalogoProdutos.services;

import com.example.catalogoProdutos.domain.Categoria;
import com.example.catalogoProdutos.repositories.CategoriaRepository;
import jakarta.transaction.Transactional;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Transactional
    public List<Categoria> listarTodos() {
        return categoriaRepository.findByStatusNot(-1);
    }

    @Transactional
    public Optional<Categoria> findById(int id) {

        return categoriaRepository.findById(id);
    }

    @Transactional
    public void adicionarCategoria(Categoria categoria) {
        categoriaRepository.save(categoria);
    }

    @Transactional
    public void updateCategoria(int id, Categoria categoria) {
        Categoria existingCategoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada"));

        existingCategoria.setNome(categoria.getNome());
        existingCategoria.setStatus(categoria.getStatus());

        categoriaRepository.save(existingCategoria);
    }

    @Transactional
    public void removerCategoria(int id) {
        Categoria existingCategoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada"));

        existingCategoria.setStatus(-1);  // Marcando como "excluída" usando status
        categoriaRepository.save(existingCategoria);
    }

    public boolean existsById(int id) {
        return categoriaRepository.existsById(id);
    }
}
