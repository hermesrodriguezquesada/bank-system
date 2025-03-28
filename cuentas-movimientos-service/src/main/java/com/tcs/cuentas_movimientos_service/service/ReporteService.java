package com.tcs.cuentas_movimientos_service.service;

import com.tcs.cuentas_movimientos_service.dto.ReporteEstadoCuentaDTO;
import java.time.LocalDate;

public interface ReporteService {
    ReporteEstadoCuentaDTO generarReporteEstadoCuenta(Long clienteId, LocalDate fechaInicio, LocalDate fechaFin);
} 