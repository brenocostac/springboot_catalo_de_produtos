package com.example.catalogoProdutos.controllers;

import com.example.catalogoProdutos.domain.Estoque;
import com.example.catalogoProdutos.domain.EstoqueResponse;
import com.example.catalogoProdutos.services.EstoqueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/estoque")
public class EstoqueController {

    private final EstoqueService estoqueService;

    public EstoqueController(EstoqueService estoqueService) {
        this.estoqueService = estoqueService;
    }


    @GetMapping
    public ResponseEntity<List<EstoqueResponse>> listarEstoques() {
        List<EstoqueResponse> estoques = estoqueService.listarTodos();
        return new ResponseEntity<>(estoques, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<EstoqueResponse> obterEstoque(@PathVariable int id) {
        Optional<EstoqueResponse> estoque = estoqueService.findById(id);
        return estoque.map(e -> new ResponseEntity<>(e, HttpStatus.OK))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }


    @PostMapping
    public ResponseEntity<Void> adicionarEstoque(@RequestBody Estoque estoque) {
        estoqueService.adicionarEstoque(estoque);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarEstoque(@PathVariable int id, @RequestBody Estoque estoque) {
        if (!estoqueService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        estoqueService.updateEstoque(id, estoque);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/atualizar_quantidade/{id}")
    public ResponseEntity<Void> atualizarEstoque(@PathVariable int id, @RequestBody int quantidade) {
        if (!estoqueService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        estoqueService.atualizarEstoqueQuantidade(id, quantidade);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerEstoque(@PathVariable int id) {
        if (!estoqueService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        estoqueService.removerEstoque(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}