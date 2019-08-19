package com.mciefova.ataccamatask.connection.data.business;

import com.mciefova.ataccamatask.connection.data.model.dto.ConnectionEntityDTO;
import com.mciefova.ataccamatask.connection.data.model.entities.ConnectionEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConnectionDTOService {

    private ModelMapper modelMapper = new ModelMapper();

    public ConnectionEntityDTO connectionEntityToDtoObject (ConnectionEntity connectionEntity) {
        return modelMapper.map(connectionEntity, ConnectionEntityDTO.class);
    }

    public List<ConnectionEntityDTO> connectionEntityToDtoObject (List<ConnectionEntity> connectionEntityList) {
        return connectionEntityList
                .stream()
                .map(this::connectionEntityToDtoObject)
                .collect(Collectors.toList());
    }

    public ConnectionEntity connectionDtoToEntityObject (ConnectionEntityDTO connectionEntity) {
        return modelMapper.map(connectionEntity, ConnectionEntity.class);
    }
}
