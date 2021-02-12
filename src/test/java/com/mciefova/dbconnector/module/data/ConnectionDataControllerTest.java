package com.mciefova.dbconnector.module.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mciefova.dbconnector.module.data.model.dto.ConnectionEntityRequest;
import com.mciefova.dbconnector.module.data.model.dto.ConnectionEntityResponse;
import io.github.benas.randombeans.api.EnhancedRandom;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test class for {@link ConnectionDataController}.
 */
@WebMvcTest(ConnectionDataController.class)
public class ConnectionDataControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private ConnectionDataController connection;

    @Test
    public void getConnections() throws Exception {

        List<ConnectionEntityResponse> expected = createResponse(5);
        given(connection.listAllConnections(any(Optional.class)))
                .willReturn(expected);

        mvc.perform(get("/connections"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(expected)));
    }

    @Test
    public void createConnection() throws Exception {

        ConnectionEntityRequest request = createRequest();
        ConnectionEntityResponse expected = createResponse(request);

        given(connection.createConnection(any(ConnectionEntityRequest.class)))
                .willReturn(expected);

        mvc.perform(post(new URI("/connections"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request)))
                .andExpect(status().is(201))
                .andExpect(content().json(mapper.writeValueAsString(expected)));
    }

    @ParameterizedTest
    @MethodSource("connectionBadRequest")
    public void createConnection_badRequest(ConnectionEntityRequest request) throws Exception {
        mvc.perform(post("/connections")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    private static Stream<Arguments> connectionBadRequest() {
        ConnectionEntityRequest emptyName = EnhancedRandom.random(ConnectionEntityRequest.class);
        emptyName.setName("");

        ConnectionEntityRequest blankHost = EnhancedRandom.random(ConnectionEntityRequest.class);
        blankHost.setHost(" ");

        return Stream.of(
                Arguments.of(EnhancedRandom.random(ConnectionEntityRequest.class, new String[]{"name"})),
                Arguments.of(EnhancedRandom.random(ConnectionEntityRequest.class, new String[]{"host"})),
                Arguments.of(EnhancedRandom.random(ConnectionEntityRequest.class, new String[]{"port"})),
                Arguments.of(EnhancedRandom.random(ConnectionEntityRequest.class, new String[]{"databaseName"})),
                Arguments.of(EnhancedRandom.random(ConnectionEntityRequest.class, new String[]{"databaseType"})),
                Arguments.of(EnhancedRandom.random(ConnectionEntityRequest.class, new String[]{"userName"})),
                Arguments.of(EnhancedRandom.random(ConnectionEntityRequest.class, new String[]{"password"})),
                Arguments.of(emptyName),
                Arguments.of(blankHost)
        );
    }

    @Test
    public void updateConnection() throws Exception {

        ConnectionEntityRequest request = createRequest();
        ConnectionEntityResponse expected = createResponse(request);

        given(connection.updateConnection(eq(expected.getId()), any(ConnectionEntityRequest.class)))
                .willReturn(expected);

        mvc.perform(put("/connections/" + expected.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(expected)));
    }

    @Test
    public void deleteConnection() throws Exception {
        long id = 121L;

        mvc.perform(delete("/connections/" + id))
                .andExpect(status().isNoContent());
    }

    private ConnectionEntityRequest createRequest() {
        String[] excludeFields = {};
        return EnhancedRandom
                .random(ConnectionEntityRequest.class, excludeFields);
    }

    private ConnectionEntityResponse createResponse(ConnectionEntityRequest request) {
        ConnectionEntityResponse response = new ConnectionEntityResponse();
        response.setId(new Random().nextLong());
        response.setName(request.getName());
        response.setHost(request.getHost());
        response.setPort(request.getPort());
        response.setDatabaseName(request.getDatabaseName());
        response.setDatabaseType(request.getDatabaseType());
        response.setUserName(request.getUserName());
        response.setPassword(request.getPassword());

        return response;
    }

    private List<ConnectionEntityResponse> createResponse(int size) {
        String[] excludeFields = {};

        return EnhancedRandom
                .randomListOf(size,
                              ConnectionEntityResponse.class,
                              excludeFields);
    }
}
