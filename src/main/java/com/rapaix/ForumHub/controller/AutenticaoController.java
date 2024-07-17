package com.rapaix.ForumHub.controller;


import com.rapaix.ForumHub.config.TokenService;
import com.rapaix.ForumHub.model.Usuario;
import com.rapaix.ForumHub.model.dto.DadosAutenticacao;
import com.rapaix.ForumHub.config.TokenJWT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/login")
public class AutenticaoController {

    private AuthenticationManager manager;

    private TokenService tokenService;

    public AutenticaoController(@Autowired AuthenticationManager manager, @Autowired TokenService tokenService) {
        this.manager = manager;
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados){
        var autehenticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        var authentication = manager.authenticate(autehenticationToken);

        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());

        return ResponseEntity.ok(new TokenJWT(tokenJWT));
    }
}
