package com.hortifood.demo.repository.EntregadorRepository;

import com.hortifood.demo.entity.entregador.Entregador.EnderecoEntregadorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EntregadorEnderecoRepository extends JpaRepository<EnderecoEntregadorEntity, Long> {
    Optional<EnderecoEntregadorEntity> findFirstByCep(String cep);
}
