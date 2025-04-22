package com.hortifood.demo.controller;

import com.hortifood.demo.dto.Inside.ProdutoDTO;
import com.hortifood.demo.entity.Produto.Produto;
import com.hortifood.demo.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/produtocontroller")
@CrossOrigin("*")
public class ProdutoController {

    @Autowired
    ProdutoService produtoService;

    @PostMapping("/criarproduto")
    public void criarProduto(@RequestBody ProdutoDTO produtoDTO){
        produtoService.criarProduto(produtoDTO.getNome(), produtoDTO.getDescricao(), produtoDTO.getPreco(), produtoDTO.getTipoProduto());
    }

    @PutMapping("/editarproduto")
    public void editarProduto(@RequestBody ProdutoDTO produtoDTO){
        produtoService.editarProduto(produtoDTO.getId(),produtoDTO.getNome(),produtoDTO.getTipoProduto(),produtoDTO.getPreco(),produtoDTO.getDescricao());
    }

    @DeleteMapping("/removerproduto")
    public void removerProduto(@RequestBody ProdutoDTO produtoDTO){
        produtoService.removerProduto(produtoDTO.getId());
    }

    @GetMapping("/buscarproduto")
    public Produto buscarProduto(@RequestBody ProdutoDTO produtoDTO){
        return produtoService.buscarProdutoPorNome(produtoDTO.getNome());
    }

}
