package com.example.catalogoProdutos.controllers;

import java.util.List;
import java.util.Optional;

import com.example.catalogoProdutos.domain.Empresa;
import com.example.catalogoProdutos.services.EmpresaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping(value = "/empresa")
public class EmpresaController {

    private final EmpresaService empresaService;

    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @GetMapping
    public ResponseEntity<List<Empresa>> listarEmpresas() {
        List<Empresa> empresas = empresaService.listarTodos();
        return new ResponseEntity<>(empresas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empresa> obterEmpresa(@PathVariable int id) {
        Optional<Empresa> empresa = empresaService.findById(id);
        return empresa.map(e -> new ResponseEntity<>(e, HttpStatus.OK))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<Empresa> adicionarEmpresa(@RequestBody Empresa empresa) {
        empresaService.adicionarEmpresa(empresa);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarEmpresa(@PathVariable int id, @RequestBody Empresa empresa) {

        if (!empresaService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        empresaService.updateEmpresa(id, empresa);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerEmpresa(@PathVariable int id) {
        if (!empresaService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        empresaService.removerEmpresa(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}