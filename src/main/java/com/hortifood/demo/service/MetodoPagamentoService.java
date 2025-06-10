package com.hortifood.demo.service;

import com.hortifood.demo.entity.cliente.Cliente;
import com.hortifood.demo.entity.metodoPagamento.MetodoPagamento;
import com.hortifood.demo.repository.clienterepository.ClientRepository;
import com.hortifood.demo.repository.clienterepository.MetodoPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MetodoPagamentoService {

    @Autowired
    ClientRepository clientRepository;
    @Autowired
    MetodoPagamentoRepository metodoPagamentoRepository;

    public boolean criarMetodoPagamento(Long id, LocalDate dataVencimento, int cvv, String numero) {
            Optional<Cliente> cliente = clientRepository.findFirstById(id);

            if (cliente.isPresent()) {

                MetodoPagamento metodoPagamento = new MetodoPagamento();
                metodoPagamento.setNumero(numero);
                metodoPagamento.setDataVencimento(dataVencimento);
                metodoPagamento.setCvv(cvv);
                metodoPagamento.setCliente(cliente.get());

                metodoPagamentoRepository.save(metodoPagamento);

                return true;
            }

            return false;
    }

    public boolean removerMetodoPagamento(Long usuarioId, Long metodoPagamentoId) {
        Optional<MetodoPagamento> metodoPagamentoOpt = metodoPagamentoRepository.findById(metodoPagamentoId);
        if (metodoPagamentoOpt.isEmpty()) {
            return false;
        }
        MetodoPagamento metodoPagamento = metodoPagamentoOpt.get();
        if (metodoPagamento.getCliente() == null || !metodoPagamento.getCliente().getId().equals(usuarioId)) {
            return false;
        }
        metodoPagamentoRepository.delete(metodoPagamento);
        return true;
    }

    public List<MetodoPagamento> buscarMetodosPagamentoPorClienteId(long clienteId) {
        Optional<Cliente> cliente = clientRepository.findFirstById(clienteId);
        if (cliente.isPresent()) {
            return cliente.get().getMetodoPagamentos();
        } else {
            throw new RuntimeException("Cliente não encontrado.");
        }
    }
}

