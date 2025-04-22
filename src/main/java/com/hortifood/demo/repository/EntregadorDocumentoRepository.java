package com.hortifood.demo.repository;

import com.hortifood.demo.entity.entregador.DocumentoEntregador.EntregadorDocumentosEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntregadorDocumentoRepository extends JpaRepository<EntregadorDocumentosEntity, Long> {
}
