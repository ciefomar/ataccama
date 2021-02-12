package com.mciefova.dbconnector.module.data.validator;

import com.mciefova.dbconnector.exception.BadRequestException;
import com.mciefova.dbconnector.module.data.model.dto.ConnectionEntityRequest;
import com.mciefova.dbconnector.module.data.service.ConnectionRepository;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.eq;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doReturn;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Test class for {@link ConnectionRequestValidator}.
 */
@ExtendWith(MockitoExtension.class)
public class ConnectionRequestValidatorTest {

    @InjectMocks
    private ConnectionRequestValidator validator;

    @Mock
    private ConnectionRepository repository;

    @Test
    public void validate_post() {
        String name = "name";
        doReturn(true).when(repository).existsByName(eq(name));

        ConnectionEntityRequest request = new ConnectionEntityRequest();
        request.setName(name);

        assertThrows(BadRequestException.class, () -> {
                 validator.validate(request);
             });

        doReturn(false).when(repository).existsByName(eq(name));
        assertDoesNotThrow(() -> {
            validator.validate(request);
        });
    }

    @Test
    public void validate_put() {
        long id = 121L;
        String name = "name";
        doReturn(true).when(repository).existsByNameAndIdNot(eq(name), eq(id));

        ConnectionEntityRequest request = new ConnectionEntityRequest();
        request.setName(name);

        assertThrows(BadRequestException.class, () -> {
                 validator.validate(id, request);
             });

        doReturn(false).when(repository).existsByNameAndIdNot(eq(name), eq(id));
        assertDoesNotThrow(() -> {
            validator.validate(id, request);
        });
    }
}
