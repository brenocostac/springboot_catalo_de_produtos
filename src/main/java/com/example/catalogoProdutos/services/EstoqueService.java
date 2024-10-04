package com.example.catalogoProdutos.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.catalogoProdutos.domain.EstoqueResponse;
import org.springframework.stereotype.Service;

import com.example.catalogoProdutos.domain.Estoque;
import com.example.catalogoProdutos.repositories.EstoqueRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;



@Service
public class EstoqueService {

    private final EstoqueRepository estoqueRepository;

    public EstoqueService(EstoqueRepository estoqueRepository) {
        this.estoqueRepository = estoqueRepository;
    }

    @Transactional
    public List<EstoqueResponse> listarTodos() {
        List<Estoque> estoques = estoqueRepository.findByStatusNot(-1);


        return estoques.stream()
                .map(estoque -> {
                    Long produtoId = null;


                    if (estoque.getProduto() != null) {
                        produtoId = estoque.getProduto().getId();
                    }

                    return new EstoqueResponse(
                            estoque.getId(),
                            estoque.getPrecoVenda(),
                            produtoId,
                            estoque.getQuantidadeProduto(),
                            estoque.getProduto().getNome()
                    );
                })
                .collect(Collectors.toList());
    }

    @Transactional
    public Optional<EstoqueResponse> findById(int id) {
        if (id <= 0) {
            return Optional.empty();
        }
        Optional<Estoque> estoqueOptional = estoqueRepository.findById(id);


        return estoqueOptional.map(estoque -> {
            Long produtoId = null;


            if (estoque.getProduto() != null) {
                produtoId = estoque.getProduto().getId();
            }

            return new EstoqueResponse(
                    estoque.getId(),
                    estoque.getPrecoVenda(),
                    produtoId,
                    estoque.getQuantidadeProduto(),
                    estoque.getProduto().getNome()
            );
        });
    }


    @Transactional
    public void adicionarEstoque(Estoque estoque) {
        estoqueRepository.save(estoque);
    }

    @Transactional
    public void updateEstoque(int id, Estoque estoque) {
        Estoque existingEstoque = estoqueRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Estoque não encontrado"));

        existingEstoque.setPrecoCompra(estoque.getPrecoCompra());
        existingEstoque.setPrecoVenda(estoque.getPrecoVenda());
        existingEstoque.setProduto(estoque.getProduto());
        existingEstoque.setQuantidadeProduto(estoque.getQuantidadeProduto());


        estoqueRepository.save(existingEstoque);
    }

    @Transactional
    public void removerEstoque(int id) {
        Estoque existingEstoque = estoqueRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Estoque não encontrado"));


        existingEstoque.setStatus(-1);

        estoqueRepository.save(existingEstoque);
    }

    @Transactional
    public void atualizarEstoqueQuantidade(int id, int quantidadeRemovida) {
        Estoque existingEstoque = estoqueRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Estoque não encontrado"));

        if (existingEstoque.getQuantidadeProduto() >= quantidadeRemovida) {
            int novaQuantidade = existingEstoque.getQuantidadeProduto() - quantidadeRemovida;
            existingEstoque.setQuantidadeProduto(novaQuantidade);
            estoqueRepository.save(existingEstoque);
        } else {
            throw new IllegalArgumentException("Quantidade a ser removida é maior do que o disponível no estoque");
        }
    }

    public boolean existsById(int id) {
        return estoqueRepository.existsById(id);
    }
}