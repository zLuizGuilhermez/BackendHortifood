package com.hortifood.demo.repository;

import com.hortifood.demo.entity.metodoPagamento.MetodoPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MetodoPagamentoRepository extends JpaRepository<MetodoPagamento, Long> {
    Optional<MetodoPagamento> findFirstByNumero(String numero);
}
