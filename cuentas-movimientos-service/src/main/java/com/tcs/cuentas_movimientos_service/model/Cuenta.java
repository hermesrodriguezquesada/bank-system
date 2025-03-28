package com.tcs.cuentas_movimientos_service.model;

import java.math.BigDecimal;

import com.tcs.cuentas_movimientos_service.model.enums.TipoCuenta;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cuentas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long clienteId;

    @Enumerated(EnumType.STRING)
    private TipoCuenta tipo;

    private BigDecimal saldoInicial;

    private Boolean estado;
}
