package com.personal.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Base64;

@Table(name = "exercise_entity")
@Entity(name = "exercise_entity")
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    private String description;

    private Base64 [] UrlGif;
}
