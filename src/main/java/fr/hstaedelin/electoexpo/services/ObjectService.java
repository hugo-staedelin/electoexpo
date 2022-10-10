package fr.hstaedelin.electoexpo.services;

import fr.hstaedelin.electoexpo.configuration.exceptions.NotFoundException;
import fr.hstaedelin.electoexpo.models.dto.ObjectDTO;
import fr.hstaedelin.electoexpo.models.dto.TypeDTO;
import fr.hstaedelin.electoexpo.models.job.Object;
import fr.hstaedelin.electoexpo.models.job.Type;
import fr.hstaedelin.electoexpo.repositories.ObjectRepository;
import fr.hstaedelin.electoexpo.repositories.TypeRepository;
import fr.hstaedelin.electoexpo.services.mappers.ObjectMapper;
import fr.hstaedelin.electoexpo.services.mappers.TypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ObjectService {
    private final ObjectRepository objectRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public ObjectService(ObjectRepository objectRepository, ObjectMapper objectMapper) {
        this.objectRepository = objectRepository;
        this.objectMapper = objectMapper;
    }

    public Iterable<ObjectDTO> getObjects() {
        return this.objectMapper.objectList(this.objectRepository.findAll());
    }

    public Optional<ObjectDTO> getObjectByID(Integer id) {
        return Optional.of(this.objectMapper.map(this.objectRepository.findById(id).orElseThrow(NotFoundException::new)));
    }

    public Object addObject(ObjectDTO typeDTO) {
        Object object = this.objectMapper.map(typeDTO);
        this.objectRepository.save(object);
        return object;
    }

    public Object update(Integer id, ObjectDTO objectDTO) {
        Optional<ObjectDTO> object = this.getObjectByID(id);
        if (object.isPresent()) {
            this.objectRepository.save(this.update(objectDTO, this.objectMapper.map(object.get())));
            return this.objectMapper.map(object.get());
        } else return null;
    }

    private Object update(ObjectDTO objectDTO, Object object) {
        object.setName(objectDTO.getName());
        object.setDescription(objectDTO.getDescription());
        object.setPeriod(objectDTO.getPeriod());
        object.setDateOfBorrowing(objectDTO.getDateOfBorrowing());
        object.setEndDateOfBorrowing(objectDTO.getEndDateOfBorrowing());
        return object;
    }

    public void removeAll() {
        objectRepository.deleteAll();
    }

    public void removeById(Integer id) {
        Optional<ObjectDTO> type = this.getObjectByID(id);
        type.ifPresent(objectDTO -> this.objectRepository.delete(this.objectMapper.map(objectDTO)));
    }
}
