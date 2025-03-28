package com.tcs.cuentas_movimientos_service.dto;

import lombok.*;
import java.util.List;

@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class ReporteEstadoCuentaDTO {
    private Long clienteId;
    private List<CuentaReporteDTO> cuentas;
}