package com.tcs.cuentas_movimientos_service.service;

import com.tcs.cuentas_movimientos_service.dto.CuentaRequestDTO;
import com.tcs.cuentas_movimientos_service.dto.CuentaResponseDTO;

import java.util.List;

public interface CuentaService {
    CuentaResponseDTO crearCuenta(CuentaRequestDTO request);
    List<CuentaResponseDTO> obtenerCuentasPorCliente(Long clienteId);

    CuentaResponseDTO obtenerPorId(Long id);

    CuentaResponseDTO actualizar(Long id, CuentaRequestDTO request);
    
    void eliminar(Long id);

}
