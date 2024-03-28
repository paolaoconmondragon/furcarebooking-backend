package com.furcare.furcare.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.furcare.furcare.models.entities.Cliente;
import com.furcare.furcare.repositories.ClienteRepository;

@Service
public class ClienteServiceImpl implements IClienteService{

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public Cliente crearCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public List<Cliente> listarClientes() {
        return (List<Cliente>) clienteRepository.findAll();
    }
}
