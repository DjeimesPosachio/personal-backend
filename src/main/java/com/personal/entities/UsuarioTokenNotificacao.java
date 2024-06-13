package com.personal.entities;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "usuarioTokenNotificacao")
@Entity(name = "usuarioTokenNotificacao")
@Builder
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class UsuarioTokenNotificacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "id_usuario", referencedColumnName = "id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User usuario;

    @Column(name = "token", length = 200, nullable = false)
    private String token;
}
