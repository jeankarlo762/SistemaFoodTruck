package com.example.demo.service.serviceImpl;

import com.example.demo.exception.RecursoNaoEncontradoException;
import com.example.demo.exception.ValidacaoException;
import com.example.demo.model.Produto;
import com.example.demo.repository.ProdutoRepository;
import com.example.demo.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository produtoRepository;

    @Autowired
    public ProdutoServiceImpl(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Produto> buscarTodos() {
        return produtoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Produto buscaPorNome(String nome) {
        return produtoRepository.findByNome(nome)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Produto com nome '" + nome + "' não encontrado."));
    }

    @Override
    @Transactional(readOnly = true)
    public Produto buscaPorId(int id) {
        return produtoRepository.findById((long) id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Produto com ID " + id + " não encontrado."));
    }

    @Override
    @Transactional
    public Produto cadastrar(Produto produto) {
        if (produto.getId() != null) {
            throw new ValidacaoException("ID deve ser nulo para cadastrar um novo produto.");
        }
        return produtoRepository.save(produto);
    }

    @Override
    @Transactional
    public void remover(Produto produto) {
        if (produto == null || produto.getId() == null) {
            throw new ValidacaoException("Produto ou ID do produto não pode ser nulo para a remoção.");
        }

        if (!produtoRepository.existsById(produto.getId())) {
            throw new RecursoNaoEncontradoException("Produto com ID " + produto.getId() + " não encontrado.");
        }
        produtoRepository.delete(produto);
    }

    @Override
    @Transactional
    public Produto atualizar(Produto produto) {
        if (produto.getId() == null) {
            throw new ValidacaoException("ID do produto não pode ser nulo para a atualização.");
        }

        Produto produtoExistente = produtoRepository.findById(produto.getId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Produto com ID " + produto.getId() + " não encontrado."));

        produtoExistente.setNome(produto.getNome());
        produtoExistente.setDescricao(produto.getDescricao());
        produtoExistente.setPreco(produto.getPreco());
        produtoExistente.setDisponivel(produto.isDisponivel());

        return produtoRepository.save(produtoExistente);
    }
}