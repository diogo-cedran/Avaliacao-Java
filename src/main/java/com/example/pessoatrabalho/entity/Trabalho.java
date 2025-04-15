package com.example.pessoatrabalho.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "trabalhos")
public class Trabalho {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String cargo;
    
    @Column(nullable = false)
    private String empresa;
    
    private BigDecimal salario;
    
    private LocalDate dataInicio;
    
    private LocalDate dataFim;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pessoa_id", nullable = false)
    private Pessoa pessoa;
} 