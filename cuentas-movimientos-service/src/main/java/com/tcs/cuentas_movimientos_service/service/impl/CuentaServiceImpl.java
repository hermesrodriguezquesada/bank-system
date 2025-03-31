package com.tcs.cuentas_movimientos_service.service.impl;

import com.tcs.cuentas_movimientos_service.dto.CuentaRequestDTO;
import com.tcs.cuentas_movimientos_service.dto.CuentaResponseDTO;
import com.tcs.cuentas_movimientos_service.model.Cuenta;
import com.tcs.cuentas_movimientos_service.repository.CuentaRepository;
import com.tcs.cuentas_movimientos_service.service.CuentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CuentaServiceImpl implements CuentaService {

    private final CuentaRepository cuentaRepository;

    @Override
    public CuentaResponseDTO crearCuenta(CuentaRequestDTO request) {
        Cuenta cuenta = Cuenta.builder()
                .clienteId(request.getClienteId())
                .tipo(request.getTipo())
                .saldoInicial(request.getSaldoInicial())
                .estado(true)
                .build();

        Cuenta creada = cuentaRepository.save(cuenta);

        return mapToDTO(creada);
    }

    @Override
    public List<CuentaResponseDTO> obtenerCuentasPorCliente(Long clienteId) {
        List<Cuenta> cuentas = cuentaRepository.findByClienteId(clienteId);
        if (cuentas == null || cuentas.isEmpty()) {
            return Collections.emptyList();
        }
    
        return cuentas.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CuentaResponseDTO obtenerPorId(Long id) {
        Cuenta cuenta = cuentaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
        return mapToDTO(cuenta);
    }

    @Override
    public CuentaResponseDTO actualizar(Long id, CuentaRequestDTO request) {
        Cuenta cuenta = cuentaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
        cuenta.setTipo(request.getTipo());
        cuenta.setSaldoInicial(request.getSaldoInicial());
        cuenta.setClienteId(request.getClienteId());
        return mapToDTO(cuentaRepository.save(cuenta));
    }

    @Override
    public void eliminar(Long id) {
        Cuenta cuenta = cuentaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
        cuentaRepository.delete(cuenta);
    }

    private CuentaResponseDTO mapToDTO(Cuenta cuenta) {
        return CuentaResponseDTO.builder()
                .id(cuenta.getId())
                .clienteId(cuenta.getClienteId())
                .tipo(cuenta.getTipo())
                .saldoInicial(cuenta.getSaldoInicial())
                .estado(cuenta.getEstado())
                .build();
    }
}
