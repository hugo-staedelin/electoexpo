package fr.hstaedelin.electoexpo.services;

import fr.hstaedelin.electoexpo.models.job.Type;
import fr.hstaedelin.electoexpo.repositories.TypeRepository;
import fr.hstaedelin.electoexpo.services.mappers.TypeMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = Type.class
)
@AutoConfigureMockMvc
@Slf4j
public class TypeServiceTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private TypeService service;

    private TypeMapper mapper;

    private TypeRepository repository;

    @AfterAll
    @Test
    public void initTests() throws Exception {
        this.addType(1, "prototype");
        this.shouldCreateATypeFromService(1, "image", "/v2/objects/types");
        this.shouldReadATypeFromService(1, "maquette", "/v2/objects/types");
    }
    @Test
    public Type addType(Integer id, String label) {
        Type type = new Type(id, label);

        Assertions.assertEquals(label, type.getLabel());

        return type;
    }

    @Test
    public void shouldCreateATypeFromService(Integer id, String label, String path) throws Exception {
        Type type = this.addType(id, label);

        this.mvc.perform(post(path, type))
                .andExpect(status().isCreated())
                .andExpect(content().json(type.toString()));


        Type typeEntity = this.repository.findById(id).get();

        Assertions.assertEquals(typeEntity.getLabel(), label);
    }

    @Test
    public void shouldReadATypeFromService(Integer id, String label, String path) throws Exception {
        Type type = this.addType(id, label);

        this.shouldCreateATypeFromService(id, label, path);

        this.mvc.perform(get(path))
                .andExpect(status().isOk())
                .andExpect(content().json(type.toString()));
    }

    @Test
    public void shouldUpdateATypeFromService(Integer id, String label, String path) throws Exception {
        Type type = this.addType(id, label);

        this.shouldCreateATypeFromService(id, label, path);

        type.setLabel("Test_"+label);

        this.mvc.perform(patch(path))
                .andExpect(status().isOk())
                .andExpect(content().json(type.toString()));
    }
}
