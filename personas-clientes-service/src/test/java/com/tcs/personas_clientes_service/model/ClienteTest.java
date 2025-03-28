package com.tcs.personas_clientes_service.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {

    @Test
    void testClienteBuilder() {
        Cliente cliente = Cliente.builder()
                .id(1L)
                .nombre("Juan Pérez")
                .genero("M")
                .edad(30)
                .identificacion("123456789")
                .direccion("Calle Principal 123")
                .telefono("555-0123")
                .clienteId("juanperez")
                .contrasena("1234")
                .estado(true)
                .build();

        assertEquals(1L, cliente.getId());
        assertEquals("Juan Pérez", cliente.getNombre());
        assertEquals("M", cliente.getGenero());
        assertEquals(30, cliente.getEdad());
        assertEquals("123456789", cliente.getIdentificacion());
        assertEquals("Calle Principal 123", cliente.getDireccion());
        assertEquals("555-0123", cliente.getTelefono());
        assertEquals("juanperez", cliente.getClienteId());
        assertEquals("1234", cliente.getContrasena());
        assertTrue(cliente.getEstado());
    }

    @Test
    void testClienteSettersAndGetters() {
        Cliente cliente = new Cliente();
        
        cliente.setId(1L);
        cliente.setNombre("María García");
        cliente.setGenero("F");
        cliente.setEdad(25);
        cliente.setIdentificacion("987654321");
        cliente.setDireccion("Avenida Central 456");
        cliente.setTelefono("555-9876");
        cliente.setClienteId("mariagarcia");
        cliente.setContrasena("5678");
        cliente.setEstado(true);

        assertEquals(1L, cliente.getId());
        assertEquals("María García", cliente.getNombre());
        assertEquals("F", cliente.getGenero());
        assertEquals(25, cliente.getEdad());
        assertEquals("987654321", cliente.getIdentificacion());
        assertEquals("Avenida Central 456", cliente.getDireccion());
        assertEquals("555-9876", cliente.getTelefono());
        assertEquals("mariagarcia", cliente.getClienteId());
        assertEquals("5678", cliente.getContrasena());
        assertTrue(cliente.getEstado());
    }
} 