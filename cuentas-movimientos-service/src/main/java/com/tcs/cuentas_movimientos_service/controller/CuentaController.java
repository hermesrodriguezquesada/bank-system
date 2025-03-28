package com.tcs.cuentas_movimientos_service.controller;

import com.tcs.cuentas_movimientos_service.dto.CuentaRequestDTO;
import com.tcs.cuentas_movimientos_service.dto.CuentaResponseDTO;
import com.tcs.cuentas_movimientos_service.service.CuentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cuentas")
@RequiredArgsConstructor
public class CuentaController {

    private final CuentaService cuentaService;

    @PostMapping
    public ResponseEntity<CuentaResponseDTO> crearCuenta(@RequestBody CuentaRequestDTO request) {
        return ResponseEntity.ok(cuentaService.crearCuenta(request));
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<CuentaResponseDTO>> obtenerPorCliente(@PathVariable Long clienteId) {
        return ResponseEntity.ok(cuentaService.obtenerCuentasPorCliente(clienteId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CuentaResponseDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(cuentaService.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CuentaResponseDTO> actualizar(@PathVariable Long id, @RequestBody CuentaRequestDTO request) {
        return ResponseEntity.ok(cuentaService.actualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        cuentaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}
