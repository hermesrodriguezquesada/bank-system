package com.tcs.cuentas_movimientos_service.dto;

import com.tcs.cuentas_movimientos_service.model.enums.TipoMovimiento;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovimientoResponseDTO {
    private Long id;
    private Long cuentaId;
    private LocalDate fecha;
    private TipoMovimiento tipo;
    private BigDecimal valor;
    private BigDecimal saldoDisponible;
}
