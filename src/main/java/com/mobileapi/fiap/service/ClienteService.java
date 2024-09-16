package com.mobileapi.fiap.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobileapi.fiap.model.ClienteModel;
import com.mobileapi.fiap.repository.ClienteRepository;

@Service
public class ClienteService {
    
    @Autowired
    private ClienteRepository clienteRepository;
    
    public ClienteModel createCliente(ClienteModel cliente) {
        return clienteRepository.save(cliente);
    }
    
    public List<ClienteModel> createClientes(List<ClienteModel> clientes) {
        return clienteRepository.saveAll(clientes);
    }
    
    public List<ClienteModel> getAllClientes() {
        return clienteRepository.findAll();
    }

    public Optional<ClienteModel> getClienteById(Long id) {
        return clienteRepository.findById(id);
    }
    
    public Optional<ClienteModel> updateCliente(Long id, ClienteModel clienteDetails) {
        return clienteRepository.findById(id).map(cliente -> {
            cliente.setNm_cliente(clienteDetails.getNm_cliente());
            cliente.setTp_cliente(clienteDetails.getTp_cliente());
            cliente.setDt_cadastro(clienteDetails.getDt_cadastro());
            cliente.setNmEmail(clienteDetails.getNmEmail());
            cliente.setNm_senha(clienteDetails.getNm_senha());
            return clienteRepository.save(cliente);
        });
    }
    
    public Optional<ClienteModel> patchCliente(Long id, ClienteModel clienteDetails) {
        return clienteRepository.findById(id).map(cliente -> {
            if (clienteDetails.getNm_cliente() != null) {
                cliente.setNm_cliente(clienteDetails.getNm_cliente());
            }
            if (clienteDetails.getTp_cliente() != null) {
                cliente.setTp_cliente(clienteDetails.getTp_cliente());
            }
            if (clienteDetails.getDt_cadastro() != null) {
                cliente.setDt_cadastro(clienteDetails.getDt_cadastro());
            }
            if (clienteDetails.getNmEmail() != null) {
                cliente.setNmEmail(clienteDetails.getNmEmail());
            }
            if (clienteDetails.getNm_senha() != null) {
                cliente.setNm_senha(clienteDetails.getNm_senha());
            }
            return clienteRepository.save(cliente);
        });
    }
    
    public boolean deleteCliente(Long id) {
        return clienteRepository.findById(id).map(cliente -> {
            clienteRepository.delete(cliente);
            return true;
        }).orElse(false);
    }
}
