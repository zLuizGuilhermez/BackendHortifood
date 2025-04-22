package com.hortifood.demo.repository;

import com.hortifood.demo.entity.loja.CardapioLoja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardapioLojaRepository extends JpaRepository<CardapioLoja, Long> {
    Optional<CardapioLoja> findFirstByNomeCardapio(String nome);

    Optional<CardapioLoja> findFirstByIdCardapioRestaurante(Long id);
}
