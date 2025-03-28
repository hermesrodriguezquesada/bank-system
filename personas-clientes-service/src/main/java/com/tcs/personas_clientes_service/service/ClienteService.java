package com.tcs.personas_clientes_service.service;

import com.tcs.personas_clientes_service.dto.ClienteRequestDTO;
import com.tcs.personas_clientes_service.model.Cliente;

import java.util.List;

public interface ClienteService {

    Cliente crearCliente(ClienteRequestDTO dto);

    List<Cliente> listarClientes();

    Cliente obtenerCliente(Long id);

    Cliente actualizarCliente(Long id, ClienteRequestDTO dto);

    void eliminarCliente(Long id);
}
