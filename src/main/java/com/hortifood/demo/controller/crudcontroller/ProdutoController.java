package com.hortifood.demo.controller.crudcontroller;

import com.hortifood.demo.dto.Inside.ProdutoDTO;
import com.hortifood.demo.entity.Produto.Produto;
import com.hortifood.demo.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Produto", description = "Operações relacionadas a produtos")
@RestController
@RequestMapping("/api/produtocontroller")
@CrossOrigin("*")
public class ProdutoController {

    @Autowired
    ProdutoService produtoService;

    @Operation(summary = "Criar produto", description = "Cria um novo produto.")
    @PostMapping("/criarproduto")
    public void criarProduto(@RequestBody ProdutoDTO produtoDTO){
        produtoService.criarProduto(produtoDTO.getNome(), produtoDTO.getDescricao(), produtoDTO.getPreco(), produtoDTO.getTipoProduto());
    }

    @Operation(summary = "Editar produto", description = "Edita um produto existente.")
    @PutMapping("/editarproduto")
    public void editarProduto(@RequestBody ProdutoDTO produtoDTO){
        produtoService.editarProduto(produtoDTO.getId(),produtoDTO.getNome(),produtoDTO.getTipoProduto(),produtoDTO.getPreco(),produtoDTO.getDescricao());
    }

    @Operation(summary = "Remover produto", description = "Remove um produto pelo ID.")
    @DeleteMapping("/removerproduto")
    public void removerProduto(@RequestBody ProdutoDTO produtoDTO){
        produtoService.removerProduto(produtoDTO.getId());
    }

    @Operation(summary = "Buscar produto", description = "Busca um produto pelo nome.")
    @GetMapping("/buscarproduto")
    public Produto buscarProduto(@RequestBody ProdutoDTO produtoDTO){
        return produtoService.buscarProdutoPorNome(produtoDTO.getNome());
    }

}
