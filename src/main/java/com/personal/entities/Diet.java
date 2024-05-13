package com.personal.entities;

import com.personal.dtos.request.DietRequestDto;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "dieta")
@Entity(name = "dieta")
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Diet {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    public Diet(DietRequestDto data) {

    }
}
