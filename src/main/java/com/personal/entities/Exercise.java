package com.personal.entities;


import com.personal.enums.EUserRole;
import jakarta.persistence.*;
import lombok.*;

@Table
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String nameExercicio;



}
