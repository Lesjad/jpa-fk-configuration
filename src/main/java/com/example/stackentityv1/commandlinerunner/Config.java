package com.example.stackentityv1.commandlinerunner;

import com.example.stackentityv1.model.Client;
import com.example.stackentityv1.model.User;
import com.example.stackentityv1.model.UserRole;
import com.example.stackentityv1.service.ClientService;
import com.example.stackentityv1.service.UserRoleService;
import com.example.stackentityv1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public CommandLineRunner commandLineRunner(
            @Autowired UserRoleService roleService,
            @Autowired UserService userService,
            @Autowired ClientService clientService
    ) {
        return args -> {

            //Creating 5 new roles and saving it in the repository
            for (int i = 0; i < 5; i++) {
                roleService.save(new UserRole());
            }
            //Creating 5 new users and assigning them roles
            //(user 5 -> role 1, user 4 -> role 2, ...)
            for (int i = 5; i > 0; i--) {
                userService.assignRoleToUser(
                        userService.save(new User()).getUserId(), i);
            }

            //Creating client entity and playing with it:
            //first newly created client is assign to user no 5
            int userId = 5;
            clientService.assignUserToClient(
                    clientService
                            .save(new Client())
                            .getClientId(),
                    userId);
            System.out.println("\nclient 1 after assignment of user 5:");
            clientService.printClient(1);

            //changing the assignment of user to: client 1 => user 3;
            //(having in mind that user 3 has role number 3)
            clientService.assignUserToClient(1, 3);
            System.out.println("\nclient 1 after assigning user 3: ");
            clientService.printClient(1);

            //Change the assignment of role to user 3 (without explicitly touching client entity)
            userService.assignRoleToUser(3, 1);
            System.out.println("\nclient 1 after assigning role 1 to user 3, without touching 'client' entity");
            clientService.printClient(1);

            //Doing it again with different role
            userService.assignRoleToUser(3, 2);
            System.out.println("\nclient 1 after assigning role 2 to user 3, without touching 'client' entity");
            clientService.printClient(1);
        };
    }
}
