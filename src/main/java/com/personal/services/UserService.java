package com.personal.services;

import com.personal.dtos.request.UserRequestDto;
import com.personal.dtos.response.UsuarioResponseDto;
import com.personal.entities.AlunoEntity;
import com.personal.entities.User;
import com.personal.exceptions.EventNotFoundException;
import com.personal.repositories.AlunoRepository;
import com.personal.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UsuarioResponseDto create(
            UserRequestDto dto) {
        boolean emailExistente = repository.existsByEmail(dto.getEmail());
        if (emailExistente) {
            throw new EventNotFoundException("Email ja existe");
        }

        User user = User.builder()
                .nome(dto.getNome())
                .email(dto.getEmail())
                .role(dto.getRole())
                .senha(passwordEncoder.encode(dto.getSenha()))
                .build();

        User novouser = repository.save(user);

        AlunoEntity aluno = AlunoEntity.builder()
                .nome(novouser.getNome())
                .user(user)
                .build();

        alunoRepository.save(aluno);

        return new UsuarioResponseDto(novouser);
    }

    public List<UsuarioResponseDto> findAll() {
        return repository.findAll().stream().map(UsuarioResponseDto::new).toList();
    }

    public User findById(@PathVariable Long id) {
        Optional<User> user = repository.findById(id);
        return repository.findById(id).orElseThrow(() -> new EventNotFoundException("Usuário não encontrado"));
    }

    public UsuarioResponseDto recuperarPorId(Long id) {
        User user = findById(id);

        return new UsuarioResponseDto(user);
    }

    public UsuarioResponseDto update(@PathVariable Long id,
                                     UserRequestDto dto) {

        User user = findById(id);

        user.setNome(dto.getNome());
        user.setRole(dto.getRole());
        user.setSenha(passwordEncoder.encode(dto.getSenha()));
        user.setEmail(dto.getEmail());

        User usuarioAtualizado = repository.save(user);

        AlunoEntity aluno = alunoRepository.findByUserId(id).orElse(null);

        if (aluno != null) {
            aluno.setNome(usuarioAtualizado.getNome());

            alunoRepository.save(aluno);
        }


        return new UsuarioResponseDto(usuarioAtualizado);
    }

    public void delete(@PathVariable Long id) {
        User user = findById(id);

        repository.delete(user);
    }

    public Page<UsuarioResponseDto> buscarUsuarios(Pageable pageable) {
        Page<User> usuarios = repository.findAll(pageable);

        return usuarios.map(UsuarioResponseDto::new);
    }

}
