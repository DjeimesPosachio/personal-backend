package com.personal.services;

import com.google.common.collect.Lists;
import com.personal.dtos.request.UserRequestDto;
import com.personal.dtos.response.UsuarioResponseDto;
import com.personal.entities.AlunoEntity;
import com.personal.entities.User;
import com.personal.enums.UserStatus;
import com.personal.exceptions.EventNotFoundException;
import com.personal.repositories.AlunoRepository;
import com.personal.repositories.UsuarioRepository;
import com.personal.utils.StringUtils;
import com.personal.utils.ValidatorUtils;
import com.personal.validators.PasswordValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UsuarioRepository repository;
    private final AlunoRepository alunoRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioResponseDto create(
            UserRequestDto dto) {

        ValidatorUtils.throwError(validarCreate(dto));

        PasswordValidator.compararSenhas(dto.getSenha(), dto.getConfirmarSenha());

        User user = User.builder()
                .nome(dto.getNome())
                .email(dto.getEmail())
                .role(dto.getRole())
                .senha(passwordEncoder.encode(dto.getSenha()))
                .status(UserStatus.ATIVO)
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

        ValidatorUtils.throwError(validarUpdate(dto, id));

        User user = findById(id);

        user.setNome(dto.getNome());
        user.setRole(dto.getRole());
        user.setEmail(dto.getEmail());

        User usuarioAtualizado = repository.save(user);

        AlunoEntity aluno = alunoRepository.findByUserId(id).orElse(null);

        if (aluno != null) {
            aluno.setNome(usuarioAtualizado.getNome());

            alunoRepository.save(aluno);
        }


        return new UsuarioResponseDto(usuarioAtualizado);
    }

    public void inativar(@PathVariable Long id) {
        User user = findById(id);
        user.setStatus(UserStatus.INATIVO);
        repository.save(user);
    }

    public void ativar(@PathVariable Long id) {
        User user = findById(id);
        user.setStatus(UserStatus.ATIVO);
        repository.save(user);
    }

    public Page<UsuarioResponseDto> buscarUsuarios(String nome, UserStatus status, Pageable pageable) {
        Page<User> usuarios = repository.findByFilters(nome, status, pageable);

        return usuarios.map(UsuarioResponseDto::new);
    }

    private List<String> validarCreate(UserRequestDto dto){

        List<String> errors = Lists.newArrayList();

        validar(dto, errors);

        boolean emailExistente = repository.existsByEmail(dto.getEmail());

        if(emailExistente){
            errors.add("Email já existe.");
            return errors;
        }

        return errors;

    }

    private List<String> validarUpdate(UserRequestDto dto, Long id){

        List<String> errors = Lists.newArrayList();

        validar(dto, errors);

        boolean emailExistente = repository.existsByEmailAndIdNot(dto.getEmail(), id);

        if(emailExistente){
            errors.add("Email já existe.");
            return errors;
        }


        if(Objects.isNull(id))
            errors.add("Não foi informado o usuário para editar.");

        return errors;

    }


    private void validar(UserRequestDto dto, List<String> errors){

        if(StringUtils.isBlank(dto.getEmail()))
            errors.add("E-mail é obrigatório.");

        if(StringUtils.isBlank(dto.getNome()))
            errors.add("Nome do usuário é obrigatório.");

        if(Objects.isNull(dto.getRole()))
            errors.add("Papel do usuário é obrigatório.");

    }


}
