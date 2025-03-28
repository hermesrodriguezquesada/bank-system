package com.tcs.cuentas_movimientos_service.service.impl;

import com.tcs.cuentas_movimientos_service.dto.CuentaReporteDTO;
import com.tcs.cuentas_movimientos_service.dto.MovimientoReporteDTO;
import com.tcs.cuentas_movimientos_service.dto.ReporteEstadoCuentaDTO;
import com.tcs.cuentas_movimientos_service.model.Cuenta;
import com.tcs.cuentas_movimientos_service.model.Movimiento;
import com.tcs.cuentas_movimientos_service.repository.CuentaRepository;
import com.tcs.cuentas_movimientos_service.repository.MovimientoRepository;
import com.tcs.cuentas_movimientos_service.service.ReporteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReporteServiceImpl implements ReporteService {

    private final CuentaRepository cuentaRepository;
    private final MovimientoRepository movimientoRepository;

    @Override
    public ReporteEstadoCuentaDTO generarReporteEstadoCuenta(Long clienteId, LocalDate fechaInicio, LocalDate fechaFin) {
        List<Cuenta> cuentas = cuentaRepository.findByClienteId(clienteId);
        
        return ReporteEstadoCuentaDTO.builder()
                .clienteId(clienteId)
                .cuentas(cuentas.stream()
                        .map(cuenta -> CuentaReporteDTO.builder()
                                .cuentaId(cuenta.getId())
                                .tipoCuenta(cuenta.getTipo().name())
                                .saldoInicial(cuenta.getSaldoInicial())
                                .saldoDisponible(cuenta.getSaldoInicial())
                                .estado(cuenta.getEstado())
                                .movimientos(obtenerMovimientos(cuenta.getId(), fechaInicio, fechaFin))
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }

    private List<MovimientoReporteDTO> obtenerMovimientos(Long cuentaId, LocalDate fechaInicio, LocalDate fechaFin) {
        return movimientoRepository.findByCuentaIdAndFechaBetween(cuentaId, fechaInicio, fechaFin)
                .stream()
                .map(movimiento -> MovimientoReporteDTO.builder()
                        .fecha(movimiento.getFecha())
                        .tipoMovimiento(movimiento.getTipo().name())
                        .valor(movimiento.getValor())
                        .saldoDisponible(movimiento.getSaldoDisponible())
                        .build())
                .collect(Collectors.toList());
    }
} 