package com.divyanshu.assignment.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "users")
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(unique = true,nullable = false)
    String email;
    @Column(nullable = false)
    String name;
    @Column(nullable = false)
    String phoneNumber;

    @Column(nullable = false)
    String password;
}
