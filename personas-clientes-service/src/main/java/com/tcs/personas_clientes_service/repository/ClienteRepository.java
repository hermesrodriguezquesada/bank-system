package com.tcs.personas_clientes_service.repository;

import com.tcs.personas_clientes_service.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByClienteId(String clienteId);
}
