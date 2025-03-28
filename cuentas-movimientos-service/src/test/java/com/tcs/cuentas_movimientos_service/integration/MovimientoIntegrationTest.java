package com.tcs.cuentas_movimientos_service.integration;

import com.tcs.cuentas_movimientos_service.dto.MovimientoRequestDTO;
import com.tcs.cuentas_movimientos_service.dto.MovimientoResponseDTO;
import com.tcs.cuentas_movimientos_service.model.Cuenta;
import com.tcs.cuentas_movimientos_service.model.Movimiento;
import com.tcs.cuentas_movimientos_service.model.enums.TipoCuenta;
import com.tcs.cuentas_movimientos_service.model.enums.TipoMovimiento;
import com.tcs.cuentas_movimientos_service.repository.CuentaRepository;
import com.tcs.cuentas_movimientos_service.repository.MovimientoRepository;
import com.tcs.cuentas_movimientos_service.service.MovimientoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MovimientoIntegrationTest {

    @Autowired
    private MovimientoService movimientoService;

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private MovimientoRepository movimientoRepository;

    private Cuenta cuenta;

    @BeforeEach
    void setUp() {
        cuenta = Cuenta.builder()
                .clienteId(1L)
                .tipo(TipoCuenta.CORRIENTE)
                .saldoInicial(new BigDecimal("1000.00"))
                .estado(true)
                .build();
        cuenta = cuentaRepository.save(cuenta);
    }

    @Test
    void testRegistrarMovimientoDeposito() {
        MovimientoRequestDTO dto = MovimientoRequestDTO.builder()
                .cuentaId(cuenta.getId())
                .tipo(TipoMovimiento.DEPOSITO)
                .valor(new BigDecimal("500.00"))
                .build();

        MovimientoResponseDTO response = movimientoService.registrarMovimiento(dto);

        assertNotNull(response.getId());
        assertEquals(TipoMovimiento.DEPOSITO, response.getTipo());
        assertEquals(new BigDecimal("500.00"), response.getValor());
        assertEquals(new BigDecimal("1500.00"), response.getSaldoDisponible());

        Cuenta cuentaActualizada = cuentaRepository.findById(cuenta.getId()).orElseThrow();
        assertEquals(new BigDecimal("1500.00"), cuentaActualizada.getSaldoInicial());
    }

    @Test
    void testRegistrarMovimientoRetiro() {
        MovimientoRequestDTO dto = MovimientoRequestDTO.builder()
                .cuentaId(cuenta.getId())
                .tipo(TipoMovimiento.RETIRO)
                .valor(new BigDecimal("300.00"))
                .build();

        MovimientoResponseDTO response = movimientoService.registrarMovimiento(dto);

        assertNotNull(response.getId());
        assertEquals(TipoMovimiento.RETIRO, response.getTipo());
        assertEquals(new BigDecimal("300.00"), response.getValor());
        assertEquals(new BigDecimal("700.00"), response.getSaldoDisponible());

        Cuenta cuentaActualizada = cuentaRepository.findById(cuenta.getId()).orElseThrow();
        assertEquals(new BigDecimal("700.00"), cuentaActualizada.getSaldoInicial());
    }

    @Test
    void testRegistrarMovimientoRetiroSinSaldo() {
        MovimientoRequestDTO dto = MovimientoRequestDTO.builder()
                .cuentaId(cuenta.getId())
                .tipo(TipoMovimiento.RETIRO)
                .valor(new BigDecimal("2000.00"))
                .build();

        assertThrows(RuntimeException.class, () -> {
            movimientoService.registrarMovimiento(dto);
        });

        Cuenta cuentaNoActualizada = cuentaRepository.findById(cuenta.getId()).orElseThrow();
        assertEquals(new BigDecimal("1000.00"), cuentaNoActualizada.getSaldoInicial());
    }
} 