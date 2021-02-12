package com.mciefova.dbconnector.module.data;

import com.mciefova.dbconnector.module.data.model.dto.ConnectionEntityRequest;
import com.mciefova.dbconnector.module.data.model.dto.ConnectionEntityResponse;
import com.mciefova.dbconnector.module.data.service.ConnectionDataService;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Controller defining endpoints for {@link ConnectionEntity} CRUD operations.
 */
@RestController
public class ConnectionDataController {

    private final ConnectionDataService connectionDataService;

    @Autowired
    public ConnectionDataController(ConnectionDataService connectionDataService) {
        this.connectionDataService = connectionDataService;
    }

    @GetMapping(value = "/connections")
    public List<ConnectionEntityResponse>
            listAllConnections(@RequestParam Optional<InfoSize> infoSize) {
        return connectionDataService.listAllConnections(infoSize);
    }

    @GetMapping("/connections/{id}")
    public ConnectionEntityResponse findConnection(@PathVariable Long id) {
        return connectionDataService.findConnection(id);
    }

    @PostMapping(value = "/connections")
    @ResponseStatus(HttpStatus.CREATED)
    public ConnectionEntityResponse
            createConnection(@Valid @RequestBody ConnectionEntityRequest connection) {
        return connectionDataService.createConnection(connection);
    }

    @PutMapping(value = "/connections/{id}")
    public ConnectionEntityResponse
            updateConnection(@PathVariable Long id,
                             @Valid @RequestBody ConnectionEntityRequest connection) {
        return connectionDataService.updateConnection(id, connection);
    }

    @DeleteMapping(value = "/connections/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteConnection(@PathVariable Long id) {
        connectionDataService.deleteConnection(id);
    }
}
