package com.example.stackentityv1.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "clients")
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "client_id")
    private Integer clientId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumns(
            value = {
                    @JoinColumn(name = "client_role_id", referencedColumnName = "user_role_id"),
                    @JoinColumn(name = "client_user_id", referencedColumnName = "user_id"),
            }
            ,
            foreignKey = @ForeignKey(
                    name = "FK_user_with_role",
                    foreignKeyDefinition = "FOREIGN KEY (client_user_id, client_role_id)\n" +
            "   REFERENCES users \n" +
            "       (user_id, user_role_id) \n" +
            "   ON UPDATE CASCADE\n" +
            "   ON DELETE CASCADE")
    )
    private User user;
}
