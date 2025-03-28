package com.tcs.personas_clientes_service.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ClienteRequestDTO {

    @NotBlank
    private String nombre;

    @NotBlank
    private String genero;

    @NotNull
    @Min(0)
    private Integer edad;

    @NotBlank
    private String identificacion;

    @NotBlank
    private String direccion;

    @NotBlank
    private String telefono;

    @NotBlank
    private String clienteId;

    @NotBlank
    private String contrasena;

    @NotNull
    private Boolean estado;
}
