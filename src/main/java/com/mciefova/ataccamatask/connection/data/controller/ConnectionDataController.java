package com.mciefova.ataccamatask.connection.data.controller;


import com.mciefova.ataccamatask.connection.data.business.ConnectionDataService;
import com.mciefova.ataccamatask.connection.data.business.enums.InfoSize;
import com.mciefova.ataccamatask.connection.data.model.entities.ConnectionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class ConnectionDataController {

    @Autowired
    private ConnectionDataService connectionDataService;

    @RequestMapping("/connections")
    public List<ConnectionEntity> listAllConnections() {
        return connectionDataService.listAllConnections();
    }

    @RequestMapping(value = "/connections", params = {"infoSize"})
    public List<ConnectionEntity> listAllConnections(@RequestParam InfoSize infoSize) {
        return connectionDataService.listAllConnections(infoSize);
    }

    @RequestMapping("/connections/{name}")
    public ConnectionEntity findConnection(@PathVariable String name) {
        return connectionDataService.findConnection(name);
    }

    @PostMapping(value = "/connections")
    @ResponseStatus(HttpStatus.CREATED)
    public void createConnection(@RequestBody ConnectionEntity connection) {
            connectionDataService.createConnection(connection);
    }

    @DeleteMapping(value = "/connections/{name}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteConnectionByName(@PathVariable String name) {
        connectionDataService.deleteConnection(name);
    }

    @DeleteMapping(value = "/connections")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteConnection(@RequestBody ConnectionEntity connection) {
        connectionDataService.deleteConnection(connection);
    }

    @PutMapping(value = "/connections")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateConnection(@RequestBody ConnectionEntity connection) {
        connectionDataService.updateConnection(connection);
    }

    @PatchMapping(value = "/connections/{name}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateConnection(@RequestBody Map<String, String> newValuesMap, @PathVariable String name) {
        connectionDataService.updateConnection(newValuesMap, name);
    }
}
