package com.hortifood.demo.repository.clienterepository;

import com.hortifood.demo.entity.metodoPagamento.MetodoPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MetodoPagamentoRepository extends JpaRepository<MetodoPagamento, Long> {
}
