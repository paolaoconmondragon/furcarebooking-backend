package com.furcare.furcare.service;

import java.util.List;

import com.furcare.furcare.models.entities.Cliente;

public interface IClienteService {

    Cliente crearCliente(Cliente cliente);

    List<Cliente> listarClientes();
}
