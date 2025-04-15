package com.example.pessoatrabalho.mapper;

import com.example.pessoatrabalho.dto.TrabalhoDTO;
import com.example.pessoatrabalho.entity.Trabalho;
import org.springframework.stereotype.Component;

@Component
public class TrabalhoMapper {
    
    public TrabalhoDTO toDTO(Trabalho trabalho) {
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
    
    public Trabalho toEntity(TrabalhoDTO dto) {
        if (dto == null) return null;
        
        Trabalho trabalho = new Trabalho();
        trabalho.setId(dto.getId());
        trabalho.setCargo(dto.getCargo());
        trabalho.setEmpresa(dto.getEmpresa());
        trabalho.setSalario(dto.getSalario());
        trabalho.setDataInicio(dto.getDataInicio());
        trabalho.setDataFim(dto.getDataFim());
        
        return trabalho;
    }
} 