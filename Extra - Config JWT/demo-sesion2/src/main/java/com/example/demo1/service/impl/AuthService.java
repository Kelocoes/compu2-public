package com.example.demo1.service.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo1.dto.AuthDtos.LoginRequestDTO;
import com.example.demo1.dto.AuthDtos.RegisterRequestDTO;
import com.example.demo1.dto.AuthDtos.TokenResponseDTO;
import com.example.demo1.model.User;
import com.example.demo1.model.Worker;
import com.example.demo1.service.IAuthService;
import com.example.demo1.service.IUserService;
import com.example.demo1.service.IWorkerService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthService implements IAuthService {

    private final IUserService userService;

    private final IWorkerService workerService;

    private final JwtService jwtService;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    @Override
    public TokenResponseDTO register(RegisterRequestDTO request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        Worker worker = new Worker();
        worker.setName(request.getName());
        worker.setLastname(request.getLastname());
        worker.setDocumentId(request.getDocumentId());
        worker.setUser(user);

        User userSaved = userService.save(user);
        Worker workerSaved = workerService.save(worker);

        String accessToken = jwtService.generateAccessToken(userSaved, workerSaved, null);

        return new TokenResponseDTO(accessToken, "Bearer");
    }

    @Override
    public TokenResponseDTO login(LoginRequestDTO request) {

        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        User user = userService.findByUsername(request.getUsername())
                    .orElseThrow(() -> new RuntimeException("User not found"));

        Worker worker = workerService.findByUserId(user.getId())
                .orElseThrow(() -> new RuntimeException("Worker not found"));
        String accessToken = jwtService.generateAccessToken(user, worker, auth);

        return new TokenResponseDTO(accessToken, "Bearer");
    }
}
