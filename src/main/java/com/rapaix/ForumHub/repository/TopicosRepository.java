package com.rapaix.ForumHub.repository;

import com.rapaix.ForumHub.model.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TopicosRepository extends JpaRepository<Topico, Long> {
    Page<Topico> findAllByAtivoTrue(Pageable paginacao);
}
