package com.tcs.cuentas_movimientos_service.controller;

import com.tcs.cuentas_movimientos_service.dto.MovimientoRequestDTO;
import com.tcs.cuentas_movimientos_service.dto.MovimientoResponseDTO;
import com.tcs.cuentas_movimientos_service.service.MovimientoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimientos")
@RequiredArgsConstructor
public class MovimientoController {

    private final MovimientoService movimientoService;

    @PostMapping
    public ResponseEntity<MovimientoResponseDTO> registrar(@RequestBody MovimientoRequestDTO dto) {
        return ResponseEntity.ok(movimientoService.registrarMovimiento(dto));
    }

    @GetMapping("/cuenta/{cuentaId}")
    public ResponseEntity<List<MovimientoResponseDTO>> listarPorCuenta(@PathVariable Long cuentaId) {
        return ResponseEntity.ok(movimientoService.obtenerPorCuenta(cuentaId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovimientoResponseDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(movimientoService.obtenerPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        movimientoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}
