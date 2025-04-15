package com.example.pessoatrabalho.dto;

import lombok.Data;
import java.util.List;

@Data
public class PessoaDTO {
    private Long id;
    private String nome;
    private String cpf;
    private Integer idade;
    private List<TrabalhoDTO> trabalhos;
} 