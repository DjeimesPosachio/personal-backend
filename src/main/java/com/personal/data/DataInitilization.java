package com.personal.data;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.personal.entities.ExercicioEntity;
import com.personal.entities.User;
import com.personal.enums.UserRole;
import com.personal.repositories.ExercicioRepository;
import com.personal.repositories.UsuarioRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DataInitilization implements ApplicationListener<ContextRefreshedEvent> {

    public static final String EMAIL_PADRAO = "admin@admin.com.br";
    private final ObjectMapper objectMapper;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ExercicioRepository ExerciseRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        Optional<UserDetails> user = usuarioRepository.findByEmail(EMAIL_PADRAO);

        if (!user.isPresent()) {
            User admin = new User();
            admin.setNome("admin");
            admin.setEmail(EMAIL_PADRAO);
            admin.setSenha(passwordEncoder.encode("admin1234"));
            admin.setRole(UserRole.ADMIN);
            usuarioRepository.save(admin);
        }
    }

    private List<ExercicioEntity> loadExercisesFromJson() {
        List<ExercicioEntity> exercicioEntities = new ArrayList<>();
        JsonNode json;

        try (InputStream inputStream = TypeReference.class.getResourceAsStream("/data/exercises.json")) {
            json = objectMapper.readTree(inputStream);
            if (json instanceof ArrayNode) {
                ArrayNode arrayNode = (ArrayNode) json;
                for (JsonNode exerciseNode : arrayNode) {
                    ExercicioEntity exercicioEntity = objectMapper.treeToValue(exerciseNode, ExercicioEntity.class);
                    exercicioEntities.add(exercicioEntity);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to read JSON data", e);
        }
        return exercicioEntities;
    }
}
