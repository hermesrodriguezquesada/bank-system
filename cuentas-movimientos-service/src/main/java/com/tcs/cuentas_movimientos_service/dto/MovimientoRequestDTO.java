package com.tcs.cuentas_movimientos_service.dto;

import com.tcs.cuentas_movimientos_service.model.enums.TipoMovimiento;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovimientoRequestDTO {
    private Long cuentaId;
    private TipoMovimiento tipo;
    private BigDecimal valor;
}
