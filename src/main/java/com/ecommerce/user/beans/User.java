package com.ecommerce.user.beans;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "user", indexes = {
        @Index(name = "idx_email", columnList = "email", unique = true)
})
public class User {

    @Id
    private String id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private Contact contact;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Address address;
}
