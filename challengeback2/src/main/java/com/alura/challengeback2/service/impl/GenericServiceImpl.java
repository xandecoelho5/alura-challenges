package com.alura.challengeback2.service.impl;

import com.alura.challengeback2.repository.GenericRepository;
import com.alura.challengeback2.service.GenericService;
import org.modelmapper.ModelMapper;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class GenericServiceImpl<T, ID, Y> implements GenericService<T, ID, Y> {

    protected abstract GenericRepository<T, ID> getRepository();

    protected abstract ModelMapper getMapper();

    protected abstract void validaJaExisteRegistroComDescricaoParaOMes(Y dto);

    protected abstract void changeEntityId(ID id, T entity);

    protected void preCadastrar(T entity, ID id) {
        if (id != null) {
            changeEntityId(id, entity);
        }
    }

    private final Class<T> entityClass;
    private final Class<Y> dtoClass;

    protected GenericServiceImpl() {
        this.entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        this.dtoClass = (Class<Y>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[2];
    }

    @Override
    public List<Y> listar() {
        List<T> list = getRepository().findAll();
        return mapEntityListToDto(list);
    }

    @Override
    public Y cadastrar(Y dto) {
        return salvar(dto, null);
    }

    @Override
    public Optional<Y> atualizar(ID id, Y dto) {
        if (getRepository().findById(id).isPresent()) {
            return Optional.of(salvar(dto, id));
        }
        return Optional.empty();
    }

    private Y salvar(Y dto, ID id) {
        validaJaExisteRegistroComDescricaoParaOMes(dto);
        T entity = mapDtoToEntity(dto);
        preCadastrar(entity, id);
        entity = getRepository().save(entity);
        return mapEntityToDto(entity);
    }

    @Override
    @Transactional
    public Optional<Y> findById(ID id) {
        Optional<T> byId = getRepository().findById(id);
        return byId.map(this::mapEntityToDto);
    }

    @Override
    @Transactional
    public Optional<Boolean> excluir(ID id) {
        if (getRepository().findById(id).isPresent()) {
            getRepository().deleteById(id);
            return Optional.of(true);
        }
        return Optional.empty();
    }

    @Override
    public Optional<T> findByDescricaoAndDataMes(String descricao, Integer ano, Integer mes) {
        return getRepository().findByDescricaoAndDataMes(descricao, ano, mes);
    }

    @Override
    public List<Y> findAllByDescricaoContaining(String descricao) {
        List<T> allByDescricaoContaining = getRepository().findAllByDescricaoContaining(descricao);
        return mapEntityListToDto(allByDescricaoContaining);
    }

    @Override
    public List<Y> findAllByAnoAndMes(Integer ano, Integer mes) {
        List<T> allByAnoAndMes = getRepository().findAllByAnoAndMes(ano, mes);
        return mapEntityListToDto(allByAnoAndMes);
    }

    private Y mapEntityToDto(T entity) {
        return getMapper().map(entity, dtoClass);
    }

    private T mapDtoToEntity(Y dto) {
        return getMapper().map(dto, entityClass);
    }

    private List<Y> mapEntityListToDto(List<T> entityList) {
        return entityList.stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
    }

    private List<T> mapDtoListToEntity(List<Y> dtoList) {
        return dtoList.stream()
                .map(this::mapDtoToEntity)
                .collect(Collectors.toList());
    }
}
