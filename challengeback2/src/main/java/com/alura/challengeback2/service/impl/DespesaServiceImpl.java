package com.alura.challengeback2.service.impl;

import com.alura.challengeback2.dto.DespesaDto;
import com.alura.challengeback2.exception.RegistroComDescricaoIgualNoMesmoMesException;
import com.alura.challengeback2.exception.RegistroNaoEncontradoException;
import com.alura.challengeback2.model.Categoria;
import com.alura.challengeback2.model.Despesa;
import com.alura.challengeback2.repository.DespesaRepository;
import com.alura.challengeback2.repository.GenericRepository;
import com.alura.challengeback2.service.DespesaService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DespesaServiceImpl extends GenericServiceImpl<Despesa, Long, DespesaDto> implements DespesaService {

    private final DespesaRepository repository;
    private final ModelMapper modelMapper;

    @Override
    protected GenericRepository<Despesa, Long> getRepository() {
        return repository;
    }

    @Override
    protected ModelMapper getMapper() {
        return modelMapper;
    }

    @Override
    protected DespesaDto validaSeJaExiste(Long id) {
        return findById(id).orElseThrow(() -> new RegistroNaoEncontradoException("Despesa", id));
    }

    @Override
    protected void validaJaExisteRegistroComDescricaoParaOMes(DespesaDto despesaDto) {
        findByDescricaoAndDataMes(despesaDto.getDescricao(), despesaDto.getData().getYear(), despesaDto.getData().getMonthValue())
                .ifPresent(r -> {
                    throw new RegistroComDescricaoIgualNoMesmoMesException("despesa");
                });
    }

    @Override
    protected void changeEntityId(Long id, Despesa despesa) {
        despesa.setId(id);
    }

    @Override
    protected void preCadastrar(Despesa despesa, Long id) {
        if (despesa.getCategoria() == null) {
            despesa.setCategoria(Categoria.OUTRAS);
        }
        super.preCadastrar(despesa, id);
    }
}
