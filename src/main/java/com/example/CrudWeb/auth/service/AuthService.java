package com.example.CrudWeb.auth.service;

import com.example.CrudWeb.auth.model.Role;
import com.example.CrudWeb.auth.model.User;
import com.example.CrudWeb.auth.repository.RoleRepository;
import com.example.CrudWeb.auth.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.util.Optional;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void registerUser(User user) {
        User existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser != null) {
            throw new RuntimeException("There is already an account registered with that email.");
        }

        User existingUserByUsername = userRepository.findByUsername(user.getUsername());
        if (existingUserByUsername != null) {
            throw new RuntimeException("There is already an account registered with that username.");
        }

        Optional<Role> userRoleOpt = roleRepository.findByName("ADMIN");
        Role userRole;
        if (userRoleOpt.isPresent()) {
            userRole = userRoleOpt.get();
        } else {
            userRole = new Role("USER");
            roleRepository.save(userRole);
        }
        user.getRoles().add(userRole);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}
