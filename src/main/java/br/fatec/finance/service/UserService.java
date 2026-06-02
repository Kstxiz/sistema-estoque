package br.fatec.finance.service;

import br.fatec.finance.dto.UserResponse;
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

    private UserResponse toResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCreatedAt()
        );
    }

    public List<UserResponse> findAll() {
        return userRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
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