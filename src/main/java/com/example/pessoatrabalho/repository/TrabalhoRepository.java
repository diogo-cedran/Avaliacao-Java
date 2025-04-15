package com.example.pessoatrabalho.repository;

import com.example.pessoatrabalho.entity.Trabalho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TrabalhoRepository extends JpaRepository<Trabalho, Long> {
    List<Trabalho> findByPessoaId(Long pessoaId);
} 