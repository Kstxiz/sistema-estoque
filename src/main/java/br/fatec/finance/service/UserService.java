package br.fatec.finance.service;

import br.fatec.finance.entity.User;
import br.fatec.finance.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User create(String name, String email, String password) {
        if (userRepository.existsByEmail(email)) {
            throw new RuntimeException("E-mail já cadastrado");
        }

        User user = User.builder()
                .id(UUID.randomUUID())
                .name(name)
                .email(email)
                .password(password)
                .createdAt(LocalDateTime.now())
                .build();

        return userRepository.save(user);
    }
}