package com.personal.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Base64;

@Table(name = "exercise")
@Entity(name = "exercise")
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private Long sets;

    private Long sequence;

    private String UrlGif;
}
