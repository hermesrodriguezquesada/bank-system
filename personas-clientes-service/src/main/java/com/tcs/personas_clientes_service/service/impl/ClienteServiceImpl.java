package com.tcs.personas_clientes_service.service.impl;

import com.tcs.personas_clientes_service.dto.ClienteRequestDTO;
import com.tcs.personas_clientes_service.exception.ResourceNotFoundException;
import com.tcs.personas_clientes_service.model.Cliente;
import com.tcs.personas_clientes_service.repository.ClienteRepository;
import com.tcs.personas_clientes_service.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    @Override
    public Cliente crearCliente(ClienteRequestDTO dto) {
        Cliente cliente = mapToEntity(dto);
        return clienteRepository.save(cliente);
    }

    @Override
    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente obtenerCliente(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con id: " + id));
    }

    @Override
    public Cliente actualizarCliente(Long id, ClienteRequestDTO dto) {
        Cliente cliente = obtenerCliente(id);
        cliente.setNombre(dto.getNombre());
        cliente.setGenero(dto.getGenero());
        cliente.setEdad(dto.getEdad());
        cliente.setIdentificacion(dto.getIdentificacion());
        cliente.setDireccion(dto.getDireccion());
        cliente.setTelefono(dto.getTelefono());
        cliente.setClienteId(dto.getClienteId());
        cliente.setContrasena(dto.getContrasena());
        cliente.setEstado(dto.getEstado());
        return clienteRepository.save(cliente);
    }

    @Override
    public void eliminarCliente(Long id) {
        Cliente cliente = obtenerCliente(id);
        clienteRepository.delete(cliente);
    }

    private Cliente mapToEntity(ClienteRequestDTO dto) {
        return Cliente.builder()
                .nombre(dto.getNombre())
                .genero(dto.getGenero())
                .edad(dto.getEdad())
                .identificacion(dto.getIdentificacion())
                .direccion(dto.getDireccion())
                .telefono(dto.getTelefono())
                .clienteId(dto.getClienteId())
                .contrasena(dto.getContrasena())
                .estado(dto.getEstado())
                .build();
    }
}
