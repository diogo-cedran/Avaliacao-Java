package com.example.pessoatrabalho.service;

import com.example.pessoatrabalho.dto.TrabalhoDTO;
import com.example.pessoatrabalho.entity.Pessoa;
import com.example.pessoatrabalho.entity.Trabalho;
import com.example.pessoatrabalho.mapper.TrabalhoMapper;
import com.example.pessoatrabalho.repository.PessoaRepository;
import com.example.pessoatrabalho.repository.TrabalhoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TrabalhoService {

    private final TrabalhoRepository trabalhoRepository;
    private final PessoaRepository pessoaRepository;
    private final TrabalhoMapper trabalhoMapper;

    @Transactional(readOnly = true)
    public List<TrabalhoDTO> findAll() {
        return trabalhoRepository.findAll().stream()
                .map(trabalhoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<TrabalhoDTO> findByPessoaId(Long pessoaId) {
        return trabalhoRepository.findByPessoaId(pessoaId).stream()
                .map(trabalhoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public TrabalhoDTO findById(Long id) {
        return trabalhoRepository.findById(id)
                .map(trabalhoMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Trabalho não encontrado com id: " + id));
    }

    @Transactional
    public TrabalhoDTO create(TrabalhoDTO trabalhoDTO) {
        Pessoa pessoa = pessoaRepository.findById(trabalhoDTO.getPessoaId())
                .orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrada com id: " + trabalhoDTO.getPessoaId()));
        
        Trabalho trabalho = trabalhoMapper.toEntity(trabalhoDTO);
        trabalho.setPessoa(pessoa);
        
        Trabalho savedTrabalho = trabalhoRepository.save(trabalho);
        return trabalhoMapper.toDTO(savedTrabalho);
    }

    @Transactional
    public TrabalhoDTO update(Long id, TrabalhoDTO trabalhoDTO) {
        if (!trabalhoRepository.existsById(id)) {
            throw new EntityNotFoundException("Trabalho não encontrado com id: " + id);
        }
        
        Pessoa pessoa = pessoaRepository.findById(trabalhoDTO.getPessoaId())
                .orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrada com id: " + trabalhoDTO.getPessoaId()));
        
        Trabalho trabalho = trabalhoMapper.toEntity(trabalhoDTO);
        trabalho.setId(id);
        trabalho.setPessoa(pessoa);
        
        Trabalho updatedTrabalho = trabalhoRepository.save(trabalho);
        return trabalhoMapper.toDTO(updatedTrabalho);
    }

    @Transactional
    public void delete(Long id) {
        if (!trabalhoRepository.existsById(id)) {
            throw new EntityNotFoundException("Trabalho não encontrado com id: " + id);
        }
        trabalhoRepository.deleteById(id);
    }
} 