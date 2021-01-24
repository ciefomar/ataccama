package com.mciefova.dbconnector.service;

import com.mciefova.dbconnector.data.Model;
import com.mciefova.dbconnector.data.Request;
import com.mciefova.dbconnector.data.Response;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.modelmapper.ModelMapper;

/**
 * Abstract model mapper.
 * @param <M> model entity
 * @param <N> request DTO
 * @param <T> response DTO
 */
public class AbstractMapper<M extends Model, N extends Request, T extends Response> {

    protected final ModelMapper modelMapper;

    private final Class<M> modelClass;
    private final Class<T> responseClass;

    public AbstractMapper(ModelMapper modelMapper, Class<M> modelClass,
                          Class<T> responseClass) {
        this.modelMapper = modelMapper;
        this.modelClass = modelClass;
        this.responseClass = responseClass;
    }

    public T mapToResponse(M model) {
        return modelMapper.map(model, responseClass);
    }

    public List<T>
            mapToResponse(Iterable<M> modelList) {
        return StreamSupport.stream(modelList.spliterator(), false)
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public M map(N request) {
        return modelMapper.map(request, modelClass);
    }

    public M update(M original,
                    N updateDto) {
        M update = map(updateDto);
        modelMapper.map(update, original);
        return original;
    }
}
