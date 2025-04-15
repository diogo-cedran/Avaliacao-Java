package com.example.pessoatrabalho.mapper;

import com.example.pessoatrabalho.dto.PessoaDTO;
import com.example.pessoatrabalho.dto.TrabalhoDTO;
import com.example.pessoatrabalho.entity.Pessoa;
import com.example.pessoatrabalho.entity.Trabalho;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PessoaMapper {
    
    public PessoaDTO toDTO(Pessoa pessoa) {
        if (pessoa == null) return null;
        
        PessoaDTO dto = new PessoaDTO();
        dto.setId(pessoa.getId());
        dto.setNome(pessoa.getNome());
        dto.setCpf(pessoa.getCpf());
        dto.setIdade(pessoa.getIdade());
        
        if (pessoa.getTrabalhos() != null) {
            List<TrabalhoDTO> trabalhosDTO = pessoa.getTrabalhos().stream()
                .map(this::toTrabalhoDTO)
                .collect(Collectors.toList());
            dto.setTrabalhos(trabalhosDTO);
        }
        
        return dto;
    }
    
    public Pessoa toEntity(PessoaDTO dto) {
        if (dto == null) return null;
        
        Pessoa pessoa = new Pessoa();
        pessoa.setId(dto.getId());
        pessoa.setNome(dto.getNome());
        pessoa.setCpf(dto.getCpf());
        pessoa.setIdade(dto.getIdade());
        
        return pessoa;
    }
    
    private TrabalhoDTO toTrabalhoDTO(Trabalho trabalho) {
        if (trabalho == null) return null;
        
        TrabalhoDTO dto = new TrabalhoDTO();
        dto.setId(trabalho.getId());
        dto.setCargo(trabalho.getCargo());
        dto.setEmpresa(trabalho.getEmpresa());
        dto.setSalario(trabalho.getSalario());
        dto.setDataInicio(trabalho.getDataInicio());
        dto.setDataFim(trabalho.getDataFim());
        dto.setPessoaId(trabalho.getPessoa().getId());
        
        return dto;
    }
} 