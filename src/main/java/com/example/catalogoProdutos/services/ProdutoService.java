package com.example.catalogoProdutos.services;

import com.example.catalogoProdutos.domain.Estoque;
import com.example.catalogoProdutos.domain.Produto;
import com.example.catalogoProdutos.repositories.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Transactional
    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    @Transactional
    public Optional<Produto> findById(int id) {
        if (id <= 0) {
            return Optional.empty();
        }
        return produtoRepository.findById(id);
    }

    @Transactional
    public void adicionarProduto(Produto produto) {
        produtoRepository.save(produto);
    }

    @Transactional
    public void updateProduto(int id, Produto produto) {
        Produto existingProduto = produtoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));

        existingProduto.setStatus(produto.getStatus());
        existingProduto.setCategoria(produto.getCategoria());
        existingProduto.setEmpresa(produto.getEmpresa());


        existingProduto.setEstoque(produto.getEstoque());

        produtoRepository.save(existingProduto);
    }



    @Transactional
    public void removerProduto(int id) {
        Produto existingProduto = produtoRepository.findById( id)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));


        existingProduto.setStatus(-1);

        produtoRepository.save(existingProduto);
    }

    public boolean existsById(int id) {
        return produtoRepository.existsById(id);
    }
}