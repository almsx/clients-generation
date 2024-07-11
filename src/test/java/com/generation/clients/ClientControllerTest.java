package com.generation.clients;

import com.generation.clients.entity.Client;
import com.generation.clients.service.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.generation.clients.controller.ClientController;

class ClientControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private ClientController clientController;

    @Mock
    private ClientService clientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(clientController).build();
    }

    @Test
    void testAddClient() throws Exception {
        Client client = new Client();
        client.setName("Juan");
        client.setLastName("Perez");
        client.setEmail("juan.perez@generation.org");
        client.setPhone("1234567890");

        when(clientService.addClient(any(Client.class))).thenReturn(client);

        mockMvc.perform(post("/api/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Juan\",\"lastName\":\"Perez\",\"email\":\"juan.perez@generation.org\",\"phone\":\"1234567890\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Juan"))
                .andExpect(jsonPath("$.lastName").value("Perez"))
                .andExpect(jsonPath("$.email").value("juan.perez@generation.org"))
                .andExpect(jsonPath("$.phone").value("1234567890"));

        verify(clientService, times(1)).addClient(any(Client.class));
    }

    @Test
    void testUpdateClient() throws Exception {
        Client client = new Client();
        client.setId(1L);
        client.setName("Carlos");
        client.setLastName("Gomez");
        client.setEmail("carlos.gomez@generation.org");
        client.setPhone("0987654321");

        when(clientService.updateClient(anyLong(), any(Client.class))).thenReturn(client);

        mockMvc.perform(put("/api/clients/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Carlos\",\"lastName\":\"Gomez\",\"email\":\"carlos.gomez@generation.org\",\"phone\":\"0987654321\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Carlos"))
                .andExpect(jsonPath("$.lastName").value("Gomez"))
                .andExpect(jsonPath("$.email").value("carlos.gomez@generation.org"))
                .andExpect(jsonPath("$.phone").value("0987654321"));

        verify(clientService, times(1)).updateClient(anyLong(), any(Client.class));
    }

    @Test
    void testGetClientById() throws Exception {
        Client client = new Client();
        client.setId(1L);
        client.setName("Juan");
        client.setLastName("Perez");
        client.setEmail("juan.perez@generation.org");
        client.setPhone("1234567890");

        when(clientService.getClientById(1L)).thenReturn(client);

        mockMvc.perform(get("/api/clients/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Juan"))
                .andExpect(jsonPath("$.lastName").value("Perez"))
                .andExpect(jsonPath("$.email").value("juan.perez@generation.org"))
                .andExpect(jsonPath("$.phone").value("1234567890"));

        verify(clientService, times(1)).getClientById(1L);
    }

    @Test
    void testGetAllClients() throws Exception {
        Client client1 = new Client();
        client1.setName("Juan");
        client1.setLastName("Perez");

        Client client2 = new Client();
        client2.setName("Carlos");
        client2.setLastName("Gomez");

        List<Client> clients = Arrays.asList(client1, client2);

        when(clientService.getAllClients()).thenReturn(clients);

        mockMvc.perform(get("/api/clients"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name").value("Juan"))
                .andExpect(jsonPath("$[0].lastName").value("Perez"))
                .andExpect(jsonPath("$[1].name").value("Carlos"))
                .andExpect(jsonPath("$[1].lastName").value("Gomez"));

        verify(clientService, times(1)).getAllClients();
    }

    @Test
    void testDeleteClient() throws Exception {
        doNothing().when(clientService).deleteClient(anyLong());

        mockMvc.perform(delete("/api/clients/1"))
                .andExpect(status().isNoContent());

        verify(clientService, times(1)).deleteClient(anyLong());
    }
}
