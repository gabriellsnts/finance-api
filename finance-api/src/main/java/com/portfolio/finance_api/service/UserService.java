package com.portfolio.finance_api.service;


import com.portfolio.finance_api.dto.UserRequestDTO;
import com.portfolio.finance_api.dto.UserResponseDTO;
import com.portfolio.finance_api.entity.User;
import com.portfolio.finance_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserResponseDTO createUser(UserRequestDTO request){
        if(userRepository.existsByEmail(request.email())){
           throw new IllegalArgumentException("Email already registered");
        }
        User user = new User();
        user.setName(request.name());
        user.setEmail(request.email());

        user.setPassword(passwordEncoder.encode(request.password()));

        User savedUser = userRepository.save(user);

        return toResponseDTO(savedUser);

    }

    @Transactional(readOnly = true)
    public UserResponseDTO getUserById(UUID id){

        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));

        return toResponseDTO(user);
    }

    private UserResponseDTO toResponseDTO(User user){
        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCreatedAt()
        );
    }
}
