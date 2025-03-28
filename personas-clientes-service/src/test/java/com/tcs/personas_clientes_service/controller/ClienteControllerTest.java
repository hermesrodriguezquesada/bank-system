package com.tcs.personas_clientes_service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tcs.personas_clientes_service.dto.ClienteRequestDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.tcs.personas_clientes_service.model.Cliente;
import com.tcs.personas_clientes_service.service.ClienteService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ClienteController.class)
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteService clienteService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void crearClienteExitosamente() throws Exception {
        ClienteRequestDTO request = ClienteRequestDTO.builder()
                .nombre("Jose Lema")
                .genero("Masculino")
                .edad(30)
                .identificacion("1234567890")
                .direccion("Otavalo sn y principal")
                .telefono("098254785")
                .clienteId("joselema")
                .contrasena("1234")
                .estado(true)
                .build();

        Cliente mockCliente = Cliente.builder()
                .id(1L)
                .nombre(request.getNombre())
                .genero(request.getGenero())
                .edad(request.getEdad())
                .identificacion(request.getIdentificacion())
                .direccion(request.getDireccion())
                .telefono(request.getTelefono())
                .clienteId(request.getClienteId())
                .contrasena(request.getContrasena())
                .estado(request.getEstado())
                .build();

        when(clienteService.crearCliente(any(ClienteRequestDTO.class))).thenReturn(mockCliente);

        mockMvc.perform(post("/clientes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nombre").value("Jose Lema"))
                .andExpect(jsonPath("$.clienteId").value("joselema"))
                .andExpect(jsonPath("$.estado").value(true));
    }
}
