package com.example.stackentityv1.service;

import com.example.stackentityv1.model.Client;
import com.example.stackentityv1.repository.ClientRepo;
import com.example.stackentityv1.repository.UserRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
public class ClientService {
    private ClientRepo clientRepo;
    private UserRepo userRepo;

    public ClientService(ClientRepo clientRepo, UserRepo userRepo) {
        this.clientRepo = clientRepo;
        this.userRepo = userRepo;
    }

    @Transactional
    public void assignUserToClient(int clientId, int userId) {
        userRepo.findById(userId).ifPresentOrElse(
                user -> {
                    clientRepo.findById(clientId).ifPresentOrElse(
                            client -> client.setUser(user),
                            () -> {throw new EntityNotFoundException("client with id: " + clientId + " could not be found");}
                    );
                },
                () -> {throw new EntityNotFoundException("user with id " + userId + " could not be found");}
        );
    }

    public void printClient(int i) {
        clientRepo.findById(i).ifPresentOrElse(System.out::println, EntityNotFoundException::new);
    }

    public Client save(Client client) {
        return clientRepo.save(client);
    }
}
