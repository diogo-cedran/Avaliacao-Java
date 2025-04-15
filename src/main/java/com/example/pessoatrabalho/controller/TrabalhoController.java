package com.example.pessoatrabalho.controller;

import com.example.pessoatrabalho.dto.TrabalhoDTO;
import com.example.pessoatrabalho.service.TrabalhoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trabalhos")
@RequiredArgsConstructor
public class TrabalhoController {

    private final TrabalhoService trabalhoService;

    @GetMapping
    public ResponseEntity<List<TrabalhoDTO>> findAll() {
        return ResponseEntity.ok(trabalhoService.findAll());
    }

    @GetMapping("/pessoa/{pessoaId}")
    public ResponseEntity<List<TrabalhoDTO>> findByPessoaId(@PathVariable Long pessoaId) {
        return ResponseEntity.ok(trabalhoService.findByPessoaId(pessoaId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrabalhoDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(trabalhoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<TrabalhoDTO> create(@Valid @RequestBody TrabalhoDTO trabalhoDTO) {
        return new ResponseEntity<>(trabalhoService.create(trabalhoDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrabalhoDTO> update(@PathVariable Long id, @Valid @RequestBody TrabalhoDTO trabalhoDTO) {
        return ResponseEntity.ok(trabalhoService.update(id, trabalhoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        trabalhoService.delete(id);
        return ResponseEntity.noContent().build();
    }
} 