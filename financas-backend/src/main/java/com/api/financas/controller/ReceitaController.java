package com.api.financas.controller;

import com.api.financas.dto.receita.ReceitaRequestDTO;
import com.api.financas.exceptions.GenericaException;
import com.api.financas.service.ReceitaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/receita")
public class ReceitaController {

    @Autowired
    private ReceitaService receitaService;

    @PostMapping("/criar/{id}")
    public ResponseEntity<Object> criar(@PathVariable String id, @Valid @RequestBody ReceitaRequestDTO receitaRequestDTO) {
        try {
            return ResponseEntity.ok().body(receitaService.criar(id, receitaRequestDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/alterar/{id}")
    public ResponseEntity<Object> alterar(@PathVariable String id, @Valid @RequestBody ReceitaRequestDTO receitaRequestDTO) {
        try {
            return ResponseEntity.ok().body(receitaService.alterar(id, receitaRequestDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<Object> listarTodos(@PathVariable String id) {
        return ResponseEntity.ok().body(receitaService.listarTodos(id));
    }

    @GetMapping("/listar-por-data/id/{id}/data/{data}")
    public ResponseEntity<Object> listarPorData(@PathVariable String id, @PathVariable LocalDate data)
            throws GenericaException {
        return ResponseEntity.ok().body(receitaService.listarPorData(id, data));
    }

    @GetMapping("/listar-por-receita/{id}")
    public ResponseEntity<Object> listarPorId(@PathVariable String id) throws GenericaException {
        return ResponseEntity.ok().body(receitaService.ListarPorId(id));
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarReceita(@PathVariable String id) throws GenericaException {
        receitaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
