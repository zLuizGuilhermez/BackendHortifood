// src/main/java/com/hortifood/demo/repository/LojaRepository.java
package com.hortifood.demo.repository.lojarepository;

import com.hortifood.demo.entity.loja.Loja;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface LojaRepository extends JpaRepository<Loja, Long> {
    Optional<Loja> findFirstByEmailLojaAndSenhaLoja(String emailLoja, String senhaLoja);
}