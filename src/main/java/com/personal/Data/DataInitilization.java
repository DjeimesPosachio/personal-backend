package com.personal.Data;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.personal.entities.Exercise;
import com.personal.entities.User;
import com.personal.enums.EUserRole;
import com.personal.repositories.IExerciseRepository;
import com.personal.repositories.IUserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DataInitilization implements ApplicationListener<ContextRefreshedEvent> {

    public static final String EMAIL_PADRAO = "admin@admin.com.br";

    @Autowired
    private IUserRepository usuarioRepository;

    @Autowired
    private IExerciseRepository ExerciseRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        Optional<UserDetails> user = usuarioRepository.findByEmail(EMAIL_PADRAO);
        // Verifica se já existe um usuário com o nome de usuário "admin"
        if (!user.isPresent()) {
            // Se não existir, cria um novo usuário administrador
            User admin = new User();
            admin.setName("admin");
            admin.setEmail(EMAIL_PADRAO);
            admin.setPassword(passwordEncoder.encode("admin1234"));
            admin.setRole(EUserRole.ADMIN);
            usuarioRepository.save(admin);
            // Inserir cinco exercícios

            Exercise exercise = new Exercise();

            exercise.setName("Abdutora ");
            exercise.setDescription(
                    "A máquina de");
            exercise.setUrlGif("uploads/gif/abdutora.gif");
            // Salve o exercício no repositório
            ExerciseRepository.save(exercise);

        }

    }

}
