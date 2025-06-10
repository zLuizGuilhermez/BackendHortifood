package com.hortifood.demo.repository.lojarepository;

import com.hortifood.demo.entity.loja.EnderecoLoja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoLojaRepository extends JpaRepository<EnderecoLoja, Long> {
}

