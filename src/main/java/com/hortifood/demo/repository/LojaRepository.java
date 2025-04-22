package com.hortifood.demo.repository;

import com.hortifood.demo.entity.loja.Loja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LojaRepository extends JpaRepository<Loja, Long> {
    Optional<Loja> findByEmailLoja(String email);
}
