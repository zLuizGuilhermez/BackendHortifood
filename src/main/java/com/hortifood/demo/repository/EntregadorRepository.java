package com.hortifood.demo.repository;

import com.hortifood.demo.entity.entregador.Entregador.Entregador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EntregadorRepository extends JpaRepository<Entregador, Long> {
    Optional<Entregador> findFirstByCpfEntregador(String cpf);
}