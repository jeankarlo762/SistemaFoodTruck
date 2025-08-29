package com.example.demo.controller;

import com.example.demo.model.Produto;
import com.example.demo.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    @Autowired
    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping("/BuscarTodos")
    public ResponseEntity<List<Produto>> listarTodos() {
        List<Produto> produtos = produtoService.buscarTodos();
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarPorId(@PathVariable int id) {
        Produto produto = produtoService.buscaPorId(id);
        return ResponseEntity.ok(produto);
    }

    @GetMapping("/BuscaNome/{nome}")
    public ResponseEntity<Produto> buscarPorNome(@PathVariable String nome) {
        Produto produto = produtoService.buscaPorNome(nome);
        return ResponseEntity.ok(produto);
    }

    @PostMapping("/Cadastro")
    public ResponseEntity<Produto> cadastrar(@RequestBody Produto produto) {
        Produto novoProduto = produtoService.cadastrar(produto);
        return new ResponseEntity<>(novoProduto, HttpStatus.CREATED);
    }

    @PutMapping("/AtualizarById/{id}")
    public ResponseEntity<Produto> atualizar(@PathVariable int id, @RequestBody Produto produtoAtualizado) {
        produtoAtualizado.setId(id);
        Produto produto = produtoService.atualizar(produtoAtualizado);
        return ResponseEntity.ok(produto);
    }

    @DeleteMapping("/RemoverById/{id}")
    public ResponseEntity<Void> remover(@PathVariable int id) {
        Produto produto = produtoService.buscaPorId(id);
        produtoService.remover(produto);
        return ResponseEntity.noContent().build();
    }
}