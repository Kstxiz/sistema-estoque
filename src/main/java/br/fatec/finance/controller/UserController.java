package br.fatec.finance.controller;

import br.fatec.finance.dto.UserResponse;
import br.fatec.finance.entity.User;
import br.fatec.finance.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserResponse> findAll() {
        return userService.findAll();
    }

    @PostMapping
    public User create(@RequestBody CreateUserRequest request) {
        return userService.create(
                request.name(),
                request.email(),
                request.password()
        );
    }

    public record CreateUserRequest(
            String name,
            String email,
            String password
    ) {
    }
}