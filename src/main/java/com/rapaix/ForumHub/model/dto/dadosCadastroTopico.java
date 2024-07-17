package com.rapaix.ForumHub.model.dto;


import jakarta.validation.constraints.NotBlank;

public record dadosCadastroTopico(
        Long id,
        @NotBlank
        String titulo,
        @NotBlank
        String mensagem,
        @NotBlank
        String autor,
        @NotBlank
        String curso
) {
}
