package com.rapaix.ForumHub.model.dto;

import com.rapaix.ForumHub.model.Topico;

public record DadosDetalhamentoTopico(Long id, String titulo, String mensagem, String autor, String curso) {
    public DadosDetalhamentoTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensagem(), topico.getAutor(), topico.getCurso());
    }
}
