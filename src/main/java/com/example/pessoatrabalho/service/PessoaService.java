package com.example.pessoatrabalho.service;

import com.example.pessoatrabalho.dto.PessoaDTO;
import com.example.pessoatrabalho.entity.Pessoa;
import com.example.pessoatrabalho.mapper.PessoaMapper;
import com.example.pessoatrabalho.repository.PessoaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PessoaService {

    private final PessoaRepository pessoaRepository;
    private final PessoaMapper pessoaMapper;

    @Transactional(readOnly = true)
    public List<PessoaDTO> findAll() {
        return pessoaRepository.findAll().stream()
                .map(pessoaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PessoaDTO findById(Long id) {
        return pessoaRepository.findById(id)
                .map(pessoaMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrada com id: " + id));
    }

    @Transactional
    public PessoaDTO create(PessoaDTO pessoaDTO) {
        if (pessoaRepository.existsByCpf(pessoaDTO.getCpf())) {
            throw new IllegalArgumentException("CPF já cadastrado");
        }
        
        Pessoa pessoa = pessoaMapper.toEntity(pessoaDTO);
        Pessoa savedPessoa = pessoaRepository.save(pessoa);
        return pessoaMapper.toDTO(savedPessoa);
    }

    @Transactional
    public PessoaDTO update(Long id, PessoaDTO pessoaDTO) {
        if (!pessoaRepository.existsById(id)) {
            throw new EntityNotFoundException("Pessoa não encontrada com id: " + id);
        }
        
        Pessoa pessoa = pessoaMapper.toEntity(pessoaDTO);
        pessoa.setId(id);
        Pessoa updatedPessoa = pessoaRepository.save(pessoa);
        return pessoaMapper.toDTO(updatedPessoa);
    }

    @Transactional
    public void delete(Long id) {
        if (!pessoaRepository.existsById(id)) {
            throw new EntityNotFoundException("Pessoa não encontrada com id: " + id);
        }
        pessoaRepository.deleteById(id);
    }
} 