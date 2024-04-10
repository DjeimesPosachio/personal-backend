package com.personal.entities;


import jakarta.persistence.*;
import lombok.*;

@Table  (name = "users")
@Entity (name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode (of="id")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String phone;

    private String email;

    private String imagemUrl;
}

