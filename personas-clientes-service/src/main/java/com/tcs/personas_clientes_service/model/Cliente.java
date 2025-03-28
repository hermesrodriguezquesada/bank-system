package com.tcs.personas_clientes_service.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter 
@Setter 
@NoArgsConstructor 
@AllArgsConstructor 
@SuperBuilder
public class Cliente extends Persona {

    @Column(unique = true, nullable = false)
    private String clienteId;

    private String contrasena;

    private Boolean estado;
}
