package com.alura.challengeback2.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Despesa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O campo descrição deve ser informado!")
    @Size(min = 2, max = 512)
    private String descricao;

    @NotNull(message = "O campo valor deve ser informado!")
    private double valor;

    @NotNull(message = "O campo data deve ser informado!")
    private LocalDate data;
}
