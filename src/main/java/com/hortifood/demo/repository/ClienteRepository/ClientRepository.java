package com.hortifood.demo.repository.ClienteRepository;

import com.hortifood.demo.entity.cliente.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findFirstByEmailClienteAndSenhaCliente(String emailCliente, String senha);

    Optional<Cliente> findFirstByCpf(String cpf);


}
