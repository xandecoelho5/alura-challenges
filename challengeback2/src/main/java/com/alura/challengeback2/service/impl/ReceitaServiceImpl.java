package com.alura.challengeback2.service.impl;

import com.alura.challengeback2.dto.ReceitaDto;
import com.alura.challengeback2.exception.RegistroComDescricaoIgualNoMesmoMesException;
import com.alura.challengeback2.model.Receita;
import com.alura.challengeback2.repository.GenericRepository;
import com.alura.challengeback2.repository.ReceitaRepository;
import com.alura.challengeback2.service.ReceitaService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReceitaServiceImpl extends GenericServiceImpl<Receita, Long, ReceitaDto> implements ReceitaService {

    private final ReceitaRepository repository;
    private final ModelMapper modelMapper;

    @Override
    protected GenericRepository<Receita, Long> getRepository() {
        return repository;
    }

    @Override
    protected ModelMapper getMapper() {
        return modelMapper;
    }

    @Override
    protected void validaJaExisteRegistroComDescricaoParaOMes(ReceitaDto receitaDto) {
        findByDescricaoAndDataMes(receitaDto.getDescricao(), receitaDto.getData().getYear(), receitaDto.getData().getMonthValue())
                .ifPresent(r -> {
                    throw new RegistroComDescricaoIgualNoMesmoMesException("receita");
                });
    }

    @Override
    protected void changeEntityId(Long id, Receita receita) {
        receita.setId(id);
    }
}
