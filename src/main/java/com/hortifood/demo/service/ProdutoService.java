package com.hortifood.demo.service;

import com.hortifood.demo.entity.Produto.Produto;
import com.hortifood.demo.entity.Produto.TipoProduto;
import com.hortifood.demo.repository.lojarepository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto criarProduto(String nome, String descricao, double preco, TipoProduto tipoProduto){
        Produto produto = new Produto();
        produto.setNome(nome);
        produto.setDescricao(descricao);
        produto.setPreco(preco);
        produto.setTipoProduto(tipoProduto);

        return produtoRepository.save(produto);
    }

    public void editarProduto(Long id, String nome, TipoProduto tipoProduto, double preco, String descricao){
        Optional<Produto> resultadoFind = produtoRepository.findFirstByIdProduto(id);
        if(resultadoFind.isPresent()){
            Produto produto = resultadoFind.get();
            produto.setNome(nome);
            produto.setTipoProduto(tipoProduto);
            produto.setPreco(preco);
            produto.setDescricao(descricao);

            produtoRepository.save(produto);
        }
    }

    public Produto buscarProdutoPorId(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    public void removerProduto(Long id){
        Optional<Produto> resultadoFind = produtoRepository.findFirstByIdProduto(id);

        if(resultadoFind.isPresent()){
            Produto produto = new Produto();
            produtoRepository.delete(produto);
        }
    }
}
