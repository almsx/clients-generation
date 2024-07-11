package com.generation.clients;

import com.generation.clients.entity.Client;
import com.generation.clients.repository.ClientRepository;
import com.generation.clients.service.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClientServiceTest {

    @InjectMocks
    private ClientService clientService;

    @Mock
    private ClientRepository clientRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddClient() {
        Client client = new Client();
        client.setName("Juan");
        client.setLastName("Perez");
        client.setEmail("juan.perez@generation.org");
        client.setPhone("1234567890");

        when(clientRepository.save(any(Client.class))).thenReturn(client);

        Client newClient = clientService.addClient(client);

        assertNotNull(newClient);
        assertEquals("Juan", newClient.getName());
        verify(clientRepository, times(1)).save(client);
    }

    @Test
    void testUpdateClient() {
        Client clientExist = new Client();
        clientExist.setId(1L);
        clientExist.setName("Juan");
        clientExist.setLastName("Perez");
        clientExist.setEmail("juan.perez@generation.org");
        clientExist.setPhone("1234567890");

        Client clientDetails = new Client();
        clientDetails.setName("Carlos");
        clientDetails.setLastName("Gomez");
        clientDetails.setEmail("carlos.gomez@generation.org");
        clientDetails.setPhone("0987654321");

        when(clientRepository.findById(1L)).thenReturn(Optional.of(clientExist));
        when(clientRepository.save(any(Client.class))).thenReturn(clientDetails);

        Client clientUpdate = clientService.updateClient(1L, clientDetails);

        assertNotNull(clientUpdate);
        assertEquals("Carlos", clientUpdate.getName());
        assertEquals("Gomez", clientUpdate.getLastName());
        verify(clientRepository, times(1)).findById(1L);
        verify(clientRepository, times(1)).save(any(Client.class));
    }

    @Test
    void testGetClientById() {
        Client client = new Client();
        client.setId(1L);
        client.setName("Juan");
        client.setLastName("Perez");
        client.setEmail("juan.perez@generation.org");
        client.setPhone("1234567890");

        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));

        Client getClient = clientService.getClientById(1L);

        assertNotNull(getClient);
        assertEquals("Juan", getClient.getName());
        verify(clientRepository, times(1)).findById(1L);
    }

    @Test
    void testDeleteClient() {
        Client client = new Client();
        client.setId(1L);
        client.setName("Juan");
        client.setLastName("Perez");
        client.setEmail("juan.perez@generation.org");
        client.setPhone("1234567890");

        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));
        doNothing().when(clientRepository).delete(client);

        clientService.deleteClient(1L);

        verify(clientRepository, times(1)).findById(1L);
        verify(clientRepository, times(1)).delete(client);
    }
}
