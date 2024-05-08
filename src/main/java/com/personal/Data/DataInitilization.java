package com.personal.Data;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
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
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.util.ResourceUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.personal.config.FileStorageProperties;
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
    private final ObjectMapper objectMapper;
    @Autowired
    private IUserRepository usuarioRepository;

    @Autowired
    private IExerciseRepository ExerciseRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Value("${file.upload-dir}")
    private String uploadgif;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        Optional<UserDetails> user = usuarioRepository.findByEmail(EMAIL_PADRAO);

        /*
         * files.forEach(file -> {
         * try {
         * byte[] fileBytes = Files.readAllBytes(file.toPath());
         * String originalFileName = file.getName();
         * String hash = DigestUtils.md5DigestAsHex(fileBytes);
         * String fileNameWithHash = hash + "_" + originalFileName;
         * 
         * File renamedFile = new File(file.getParent(), fileNameWithHash);
         * boolean renamed = file.renameTo(renamedFile);
         * if (renamed) {
         * System.out.println("Arquivo renomeado para: " + renamedFile.getName());
         * } else {
         * System.err.println("Falha ao renomear arquivo: " + originalFileName);
         * }
         * } catch (IOException e) {
         * e.printStackTrace();
         * }
         * });
         */
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
            List<File> files = getAllGifFiles();
            List<Exercise> exercises = files.stream().map(file -> {

                return new Exercise(file.getName(), "Descrição do exercício", "/uploads/gif" + file.getName());
            })
                    .collect(Collectors.toList());

            ExerciseRepository.saveAll(exercises);

        }

    }

    private List<Exercise> loadExercisesFromJson() {
        List<Exercise> exercises = new ArrayList<>();
        JsonNode json;

        try (InputStream inputStream = TypeReference.class.getResourceAsStream("/data/exercises.json")) {
            json = objectMapper.readTree(inputStream);
            if (json instanceof ArrayNode) {
                ArrayNode arrayNode = (ArrayNode) json;
                for (JsonNode exerciseNode : arrayNode) {
                    Exercise exercise = objectMapper.treeToValue(exerciseNode, Exercise.class);
                    exercises.add(exercise);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to read JSON data", e);
        }
        return exercises;
    }

    public List<File> getAllGifFiles() {
        List<File> gifFiles = new ArrayList<>();
        Path path = Paths.get(
                this.uploadgif)
                .toAbsolutePath().normalize();
        // Criar um objeto File para o diretório
        File directory = new File(path.toAbsolutePath().toString());

        // Verificar se o diretório existe e é realmente um diretório
        if (directory.exists() && directory.isDirectory()) {
            // Listar todos os arquivos no diretório
            File[] files = directory.listFiles();

            // Verificar cada arquivo para verificar se é um arquivo GIF
            if (files != null) {
                for (File file : files) {
                    if (file.isFile() && file.getName().toLowerCase().endsWith(".gif")) {
                        gifFiles.add(file);
                    }
                }
            }
        } else {
            System.err.println("O diretório especificado não existe ou não é um diretório válido.");
        }

        return gifFiles;
    }
}
