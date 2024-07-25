package com.api.financas.controller;

import com.api.financas.dto.ReceitaDTO;
import com.api.financas.service.ReceitaService;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/receita")
public class ReceitaController {

    @Autowired
    private ReceitaService receitaService;

    /**
     * Salva uma nova receita para o usuário especificado.
     *
     * @param id o ID do usuário
     * @param receitaDTO os dados da receita a ser salva
     * @return a resposta contendo a receita salva ou uma mensagem de erro
     */
    @PostMapping("/salvar/{id}")
    public ResponseEntity<Object> salvar(@PathVariable @NotBlank String id, @RequestBody @Validated ReceitaDTO receitaDTO) {
        try {
            return ResponseEntity.ok().body(receitaService.criar(id, receitaDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<Object> listarTodos(@PathVariable @NotBlank String id) {
        return ResponseEntity.ok().body(receitaService.listarTodos(id));
    }
}
