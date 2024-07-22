package com.freelance.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity // To specify a class as a JPA entity
@Table(name = "buyer") // To specify the table details
public class Buyer {

    @Id // ID will be the Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // For primary key generation - Uses database automatic increment
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "first_name") // For matching the field with column name
    private String firstName;

    @Column(name = "last_name")
    private String lastName;
}
