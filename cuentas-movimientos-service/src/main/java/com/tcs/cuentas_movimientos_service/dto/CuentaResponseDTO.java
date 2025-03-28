package com.tcs.cuentas_movimientos_service.dto;

import com.tcs.cuentas_movimientos_service.model.enums.TipoCuenta;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CuentaResponseDTO {
    private Long id;
    private Long clienteId;
    private TipoCuenta tipo;
    private BigDecimal saldoInicial;
    private Boolean estado;
}
