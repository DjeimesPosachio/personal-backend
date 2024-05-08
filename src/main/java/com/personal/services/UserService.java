package com.personal.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import org.springframework.web.bind.annotation.PathVariable;

import com.personal.dtos.request.UserRequestDto;
import com.personal.dtos.response.UserResponseDto;
import com.personal.entities.Email;
import com.personal.entities.User;
import com.personal.enums.EUserRole;
import com.personal.exceptions.EventNotFoundException;
import com.personal.repositories.IUserRepository;

@Service
public class UserService {
    @Autowired
    private IUserRepository repository;

    @Autowired
    private EmailService emailService;

    public UserResponseDto create(
            UserRequestDto dto) {
        Optional<UserDetails> opuser = repository.findByEmail(dto.getEmail());
        if (opuser.isPresent()) {
            throw new EventNotFoundException("Email ja existe");
        }
        User user = new User(dto);

        user.setRole(EUserRole.USER);

        User novouser = repository.save(user);
        Email email = new Email();
        email.setOwnerRef("OwnerRef");
        email.setEmailFrom("adao01eduardo@gmail.com");
        email.setEmailTo(novouser.getEmail());
        email.setSubject("SubTitutlo");
        emailService.sendEmail(email);
        return new UserResponseDto(novouser);
    }

    public List<UserResponseDto> findAll() {
        return repository.findAll().stream().map(UserResponseDto::new).toList();
    }

    public UserResponseDto findById(@PathVariable Long id) {
        Optional<User> exercise = repository.findById(id);
        return new UserResponseDto(exercise.get());
    }

    public UserResponseDto update(@PathVariable Long id,
            UserRequestDto newData) {
        Optional<User> user = repository.findById(id);
        if (user.isEmpty()) {
            throw new Error("nao existe");
        }
        User teste = user.get();

        User updatedTraining = repository.save(teste);
        return new UserResponseDto(updatedTraining);
    }

    public Long delete(@PathVariable Long id) {
        Optional<User> opexercise = repository.findById(id);
        if (opexercise.isEmpty()) {
            throw new EventNotFoundException("Nao Existe");
        }
        repository.delete(opexercise.get());
        return id;
    }
}
