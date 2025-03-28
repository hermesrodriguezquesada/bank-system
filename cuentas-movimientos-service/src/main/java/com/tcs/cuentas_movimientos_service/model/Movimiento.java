package com.tcs.cuentas_movimientos_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.tcs.cuentas_movimientos_service.model.enums.TipoMovimiento;

@Entity
@Table(name = "movimientos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Movimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long cuentaId;

    private LocalDate fecha;

    @Enumerated(EnumType.STRING)
    private TipoMovimiento tipo;

    private BigDecimal valor;

    private BigDecimal saldoDisponible;
}
