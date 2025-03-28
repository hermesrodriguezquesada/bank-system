package com.tcs.cuentas_movimientos_service.service;

import com.tcs.cuentas_movimientos_service.dto.MovimientoRequestDTO;
import com.tcs.cuentas_movimientos_service.dto.MovimientoResponseDTO;

import java.util.List;

public interface MovimientoService {
    MovimientoResponseDTO registrarMovimiento(MovimientoRequestDTO dto);
    List<MovimientoResponseDTO> obtenerPorCuenta(Long cuentaId);

    MovimientoResponseDTO obtenerPorId(Long id);
    void eliminar(Long id);

}
