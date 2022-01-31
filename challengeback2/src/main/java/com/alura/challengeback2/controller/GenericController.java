package com.alura.challengeback2.controller;

import com.alura.challengeback2.exception.RegistroComDescricaoIgualNoMesmoMesException;
import com.alura.challengeback2.exception.RegistroNaoEncontradoException;
import com.alura.challengeback2.service.GenericService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

public abstract class GenericController<T, ID, Y> {

    protected abstract GenericService<T, ID, Y> getService();

    @GetMapping
    public ResponseEntity<List<Y>> listar(@RequestParam(value = "descricao", required = false) String descricao) {
        if (descricao != null) {
            return new ResponseEntity<>(getService().findAllByDescricaoContaining(descricao), HttpStatus.FOUND);
        }
        return new ResponseEntity<>(getService().listar(), HttpStatus.FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Y> detalhar(@PathVariable @NotNull ID id) {
        Optional<Y> dto = getService().findById(id);
        return dto.map(y -> new ResponseEntity<>(y, HttpStatus.FOUND)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid Y dto) {
        try {
            Y cadastrado = getService().cadastrar(dto);
            return new ResponseEntity<>(cadastrado, HttpStatus.CREATED);
        } catch (RegistroComDescricaoIgualNoMesmoMesException e) {
            return ResponseEntity.badRequest().body("Erro ao salvar registro: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable ID id, @RequestBody Y dto) {
        try {
            Y atualizado = getService().atualizar(id, dto);
            return ResponseEntity.ok(atualizado);
        } catch (RegistroComDescricaoIgualNoMesmoMesException | RegistroNaoEncontradoException e) {
            return ResponseEntity.badRequest().body("Erro ao atualizar registro: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluir(@PathVariable @NotNull ID id) {
        try {
            getService().excluir(id);
            return ResponseEntity.noContent().build();
        } catch (RegistroNaoEncontradoException e) {
            return ResponseEntity.badRequest().body("Erro ao excluir registro: " + e.getMessage());
        }
    }

    @GetMapping("/{ano}/{mes}")
    public List<Y> findAllByAnoAndMes(@PathVariable("ano") Integer ano, @PathVariable("mes") Integer mes) {
        return getService().findAllByAnoAndMes(ano, mes);
    }
}
