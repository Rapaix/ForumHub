package com.rapaix.ForumHub.controller;

import com.rapaix.ForumHub.model.*;
import com.rapaix.ForumHub.model.dto.DadosAtualizacaoTopico;
import com.rapaix.ForumHub.model.dto.DadosDetalhamentoTopico;
import com.rapaix.ForumHub.model.dto.DadosListagemTopico;
import com.rapaix.ForumHub.model.dto.dadosCadastroTopico;
import com.rapaix.ForumHub.repository.TopicosRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.web.util.UriComponentsBuilder;



public class TopicosController {


    private final TopicosRepository repository;

    public TopicosController(@Autowired TopicosRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity cadastar(@RequestBody @Valid dadosCadastroTopico dados, UriComponentsBuilder uriBuilder){
        var topico = new Topico(dados);
        repository.save(topico);
        var uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoTopico(topico));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemTopico>> listar(Pageable paginacao){
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemTopico::new);
        return ResponseEntity.ok(page);
    }


    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoTopico atualizar){
        var topico = repository.getReferenceById(atualizar.id());
        topico.atulizarInformacoes(atualizar);

        return ResponseEntity.ok(new DadosDetalhamentoTopico (topico));
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id){
        var topico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoTopico(topico));
    }

}
