package com.example.pessoatrabalho.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class TrabalhoDTO {
    private Long id;
    private String cargo;
    private String empresa;
    private BigDecimal salario;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private Long pessoaId;
} 