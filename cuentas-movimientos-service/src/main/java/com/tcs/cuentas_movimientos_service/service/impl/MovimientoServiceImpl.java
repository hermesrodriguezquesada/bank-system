package com.tcs.cuentas_movimientos_service.service.impl;

import com.tcs.cuentas_movimientos_service.dto.MovimientoRequestDTO;
import com.tcs.cuentas_movimientos_service.dto.MovimientoResponseDTO;
import com.tcs.cuentas_movimientos_service.model.Cuenta;
import com.tcs.cuentas_movimientos_service.model.Movimiento;
import com.tcs.cuentas_movimientos_service.model.enums.TipoMovimiento;
import com.tcs.cuentas_movimientos_service.repository.CuentaRepository;
import com.tcs.cuentas_movimientos_service.repository.MovimientoRepository;
import com.tcs.cuentas_movimientos_service.service.MovimientoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovimientoServiceImpl implements MovimientoService {

    private final MovimientoRepository movimientoRepository;
    private final CuentaRepository cuentaRepository;

    @Override
    public MovimientoResponseDTO registrarMovimiento(MovimientoRequestDTO dto) {
        Cuenta cuenta = cuentaRepository.findById(dto.getCuentaId())
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));

        BigDecimal saldoActual = cuenta.getSaldoInicial();
        BigDecimal nuevoSaldo;

        if (dto.getTipo() == TipoMovimiento.RETIRO) {
            if (saldoActual.compareTo(dto.getValor()) < 0) {
                throw new RuntimeException("Saldo insuficiente para el retiro");
            }
            nuevoSaldo = saldoActual.subtract(dto.getValor());
        } else {
            nuevoSaldo = saldoActual.add(dto.getValor());
        }

        cuenta.setSaldoInicial(nuevoSaldo);
        cuentaRepository.save(cuenta);

        Movimiento movimiento = Movimiento.builder()
                .cuentaId(cuenta.getId())
                .fecha(LocalDate.now())
                .tipo(dto.getTipo())
                .valor(dto.getValor())
                .saldoDisponible(nuevoSaldo)
                .build();

        Movimiento guardado = movimientoRepository.save(movimiento);

        return mapToDTO(guardado);
    }

    @Override
    public List<MovimientoResponseDTO> obtenerPorCuenta(Long cuentaId) {
        return movimientoRepository.findByCuentaId(cuentaId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MovimientoResponseDTO obtenerPorId(Long id) {
        Movimiento m = movimientoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movimiento no encontrado"));
        return mapToDTO(m);
    }

    @Override
    public void eliminar(Long id) {
        Movimiento m = movimientoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movimiento no encontrado"));
        movimientoRepository.delete(m);
    }

    private MovimientoResponseDTO mapToDTO(Movimiento m) {
        return MovimientoResponseDTO.builder()
                .id(m.getId())
                .cuentaId(m.getCuentaId())
                .fecha(m.getFecha())
                .tipo(m.getTipo())
                .valor(m.getValor())
                .saldoDisponible(m.getSaldoDisponible())
                .build();
    }
}
