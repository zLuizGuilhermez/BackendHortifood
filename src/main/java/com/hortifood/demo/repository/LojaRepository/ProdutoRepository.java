package com.hortifood.demo.repository.LojaRepository;

import com.hortifood.demo.entity.Produto.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    Optional<Produto> findFirstByNome(String nome);
    Optional<Produto> findFirstByIdProduto(Long id);
}
