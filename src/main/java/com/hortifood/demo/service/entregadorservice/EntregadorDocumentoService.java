package com.hortifood.demo.service.entregadorservice;

import com.hortifood.demo.entity.entregador.DocumentoEntregador.EntregadorDocumentosEntity;
import com.hortifood.demo.dto.Inside.entregadordto.EntregadorDocumentoDTO;
import com.hortifood.demo.entity.entregador.Entregador.Entregador;
import com.hortifood.demo.repository.entregadorrepository.EntregadorDocumentoRepository;
import com.hortifood.demo.repository.entregadorrepository.EntregadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntregadorDocumentoService {
    @Autowired
    private EntregadorDocumentoRepository entregadorDocumentoRepository;
    @Autowired
    private EntregadorRepository entregadorRepository;


    public EntregadorDocumentosEntity buscarDocumentoPorIdEEntregador(Long documentoId, Long entregadorId) {
        return entregadorDocumentoRepository.findById(documentoId)
                .filter(d -> d.getEntregador() != null && d.getEntregador().getIdEntregador().equals(entregadorId))
                .orElseThrow(() -> new RuntimeException("Documento não encontrado ou não pertence ao entregador"));
    }

    public void deletarDocumentoPorIdEEntregador(Long documentoId, Long entregadorId) {
        EntregadorDocumentosEntity documento = buscarDocumentoPorIdEEntregador(documentoId, entregadorId);
        entregadorDocumentoRepository.delete(documento);
    }

    public List<EntregadorDocumentosEntity> listarDocumentosPorEntregadorBidirecional(Long entregadorId) {
        Entregador entregador = entregadorRepository.findById(entregadorId)
            .orElseThrow(() -> new RuntimeException("Entregador não encontrado"));
        return entregador.getDocumentos();
    }
}
