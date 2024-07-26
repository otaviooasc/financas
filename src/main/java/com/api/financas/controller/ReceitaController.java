package com.api.financas.controller;

import com.api.financas.dto.ReceitaDTO;
import com.api.financas.exceptions.ReceitaException;
import com.api.financas.service.ReceitaService;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/receita")
public class ReceitaController {

    @Autowired
    private ReceitaService receitaService;

    @PostMapping("/salvar/{id}")
    public ResponseEntity<Object> salvar(@PathVariable @NotBlank String id, @RequestBody @Validated ReceitaDTO receitaDTO) {
        try {
            return ResponseEntity.ok().body(receitaService.criar(id, receitaDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/alterar/{id}")
    public ResponseEntity<Object> alterar(@PathVariable @NotBlank String idReceita, @RequestBody @Validated ReceitaDTO receitaDTO) {
        try {
            return ResponseEntity.ok().body(receitaService.alterar(idReceita, receitaDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<Object> listarTodos(@PathVariable @NotBlank String id) {
        return ResponseEntity.ok().body(receitaService.listarTodos(id));
    }

    @GetMapping("/listar-por-data/id/{id}/data/{data}")
    public ResponseEntity<Object> listarPorData(@PathVariable @NotBlank String id, @PathVariable @NotBlank LocalDate data)
            throws ReceitaException {
        return ResponseEntity.ok().body(receitaService.listarPorData(id, data));
    }

    @DeleteMapping("/deletar-receita/{id}")
    public ResponseEntity<Void> deletarReceita(@PathVariable @NotBlank String id){
        receitaService.deletarReceita(id);
        return ResponseEntity.noContent().build();
    }
}
