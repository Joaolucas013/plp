package com.example.plp.controller;

import com.example.plp.infra.security.TokenService;
import com.example.plp.repository.UserRepository;
import com.example.plp.user.LoginUser;
import com.example.plp.user.RegistrarUsuario;
import com.example.plp.user.User;
import com.example.plp.user.UserRole;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/autenticar")
public class Autenticacao {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository repository;

    @Autowired
    TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginUser dto) {
        var userNamePass = new UsernamePasswordAuthenticationToken(dto.login(), dto.senha());
        var auth = this.authenticationManager.authenticate(userNamePass);

        var token = tokenService.gerarToken((User) auth.getPrincipal());
        return ResponseEntity.ok(token);
    }

    @PostMapping("/registrar")
    public ResponseEntity registrar(@RequestBody @Valid RegistrarUsuario dto) {
        if (this.repository.findByLogin(dto.login()) != null) {
            return ResponseEntity.badRequest().build();
        }
        String senhaCriptografada = new BCryptPasswordEncoder().encode(dto.senha());
        User user = new User(dto.login(), senhaCriptografada, dto.role());

        this.repository.save(user);

        return ResponseEntity.ok().build();
    }




}
