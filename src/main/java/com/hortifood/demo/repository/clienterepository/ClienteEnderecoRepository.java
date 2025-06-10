package com.hortifood.demo.repository.clienterepository;

import com.hortifood.demo.entity.cliente.ClienteEndereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteEnderecoRepository extends JpaRepository<ClienteEndereco, Long> {
}
