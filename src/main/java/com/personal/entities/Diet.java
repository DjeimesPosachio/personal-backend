package com.personal.entities;


import jakarta.persistence.*;
import lombok.*;

@Table(name = "diet_entity")
@Entity(name = "diet_entity")
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")


public class Diet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
