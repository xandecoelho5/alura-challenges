package com.alura.challengeback2.controller;

import com.alura.challengeback2.exception.RegistroComDescricaoIgualNoMesmoMesException;
import com.alura.challengeback2.exception.RegistroNaoEncontradoException;
import com.alura.challengeback2.service.GenericService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;

public abstract class GenericController<T, ID extends Serializable> {

    protected abstract GenericService<T, ID> getService();

    protected abstract T validaSeJaExiste(ID id);

    protected abstract void validaJaExisteRegistroComDescricaoParaOMes(T t);

    @GetMapping
    public List<T> listar() {
        return getService().findAll();
    }

    @PostMapping
    public ResponseEntity<String> cadastrar(@RequestBody @Valid T t) {
        try {
            validaJaExisteRegistroComDescricaoParaOMes(t);
            getService().save(t);
            return new ResponseEntity<>("Registro salvo com sucesso!", HttpStatus.CREATED);
        } catch (RegistroComDescricaoIgualNoMesmoMesException e) {
            return ResponseEntity.badRequest().body("Erro ao salvar registro: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<T> detalhar(@PathVariable ID id) {
        try {
            return ResponseEntity.ok(validaSeJaExiste(id));
        } catch (RegistroNaoEncontradoException e) {
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> atualizar(@PathVariable ID id, @RequestBody T t) {
        try {
            validaSeJaExiste(id);
            validaJaExisteRegistroComDescricaoParaOMes(t);
            getService().save(t);
            return ResponseEntity.ok("Registro atualizado com sucesso!");
        } catch (RegistroComDescricaoIgualNoMesmoMesException | RegistroNaoEncontradoException e) {
            return ResponseEntity.badRequest().body("Erro ao atualizar registro: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirReceita(@PathVariable ID id) {
        try {
            validaSeJaExiste(id);
            getService().deleteById(id);
            return ResponseEntity.ok("Registro atualizado com sucesso!");
        } catch (RegistroNaoEncontradoException e) {
            return ResponseEntity.badRequest().body("Erro ao atualizar registro: " + e.getMessage());
        }
    }
}
