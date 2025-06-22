// src/main/java/com/hortifood/demo/repository/LojaRepository.java
package com.hortifood.demo.repository.lojarepository;

import com.hortifood.demo.entity.loja.Loja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LojaRepository extends JpaRepository<Loja, Long> {
    Optional<Loja> findFirstByEmailLojaAndSenhaLoja(String emailLoja, String senhaLoja);
    Optional<Loja> findFirstByEmailLoja(String emailLoja);
}