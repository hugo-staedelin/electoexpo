package fr.hstaedelin.electoexpo.web.controllers;

import fr.hstaedelin.electoexpo.models.dto.ObjectDTO;
import fr.hstaedelin.electoexpo.models.dto.TypeDTO;
import fr.hstaedelin.electoexpo.models.job.Object;
import fr.hstaedelin.electoexpo.models.job.Type;
import fr.hstaedelin.electoexpo.repositories.ObjectRepository;
import fr.hstaedelin.electoexpo.services.ObjectService;
import fr.hstaedelin.electoexpo.services.mappers.ObjectMapper;
import fr.hstaedelin.electoexpo.services.mappers.TypeMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.status;

@RestController
@CrossOrigin
@Slf4j
public class ObjectController {
    @Deprecated
    ObjectRepository objectRepository;

    ObjectMapper objectMapper;
    TypeMapper typeMapper;
    ObjectService objectService;

    @Autowired
    public ObjectController(ObjectMapper objectMapper, TypeMapper typeMapper, ObjectService objectService) {
        this.objectMapper = objectMapper;
        this.typeMapper = typeMapper;
        this.objectService = objectService;
    }

    /**
     * @deprecated
     * This method is deprecated since the v2 API release.
     * <p> Use {@link ObjectController#findAllObjects()} instead.
     */
    @GetMapping("/v1/objects/")
    @Deprecated(since = "v2")
    public Iterable<Object> getAllObjects() {
        return objectRepository.findAll();
    }
    @GetMapping("/v2/objects/")
    public Iterable<ObjectDTO> findAllObjects() {
        return this.objectService.getObjects();
    }

    /**
     * @deprecated
     * This method is deprecated since the v2 API release.
     * <p> Use {@link ObjectController#findObjectById(Integer)} instead.
     */
    @GetMapping("/v1/objects/{id}")
    @Deprecated(since = "v2")
    public Optional<Object> getObjectById(@PathVariable Integer id) {
        return objectRepository.findById(id);
    }

    @GetMapping("/v2/objects/{id}")
    public Optional<ObjectDTO> findObjectById(@PathVariable Integer id) {
        return this.objectService.getObjectByID(id);
    }

    /**
     * @deprecated
     * This method is deprecated since the v2 API release.
     * <p> Use {@link ObjectController#createObject(ObjectDTO)} instead.
     */
    @PostMapping("/v1/objects/")
    @Deprecated(since = "v2")
    public ResponseEntity<Object> addObject(@RequestBody ObjectDTO objectDTO) {
        Object type = objectRepository.save(new Object(objectDTO));
        if (type == null) {
            return status(HttpStatus.NOT_IMPLEMENTED).build();
        } return new ResponseEntity<Object>(type, HttpStatus.CREATED);
    }

    @PostMapping("/v2/objects/")
    public ResponseEntity<ObjectDTO> createObject(@RequestBody ObjectDTO objectDTO) {
        Object object = this.objectService.addObject(objectDTO);
        if (object == null) {
            return status(HttpStatus.NOT_IMPLEMENTED).build();
        } return ResponseEntity.status(HttpStatus.CREATED).body(this.objectMapper.map(object));
    }

    /**
     * @deprecated
     * This method is deprecated since the v2 API release.
     * <p> Use {@link ObjectController#updateObject(Integer, ObjectDTO)} instead.
     */
    @PatchMapping("/v1/objects/{id}")
    @Deprecated(since = "v2")
    public ResponseEntity<Object> editObject(@PathVariable Integer id, @RequestBody ObjectDTO objectDTO) throws IOException {
        Object obj = objectRepository.findById(id).orElseThrow(IOException::new);
        obj.setDescription(objectDTO.getDescription());
        obj.setName(objectDTO.getName());
        obj.setDateOfBorrowing(objectDTO.getDateOfBorrowing());
        obj.setEndDateOfBorrowing(objectDTO.getEndDateOfBorrowing());
        obj.setPeriod(objectDTO.getPeriod());
        final Object updatedObj = objectRepository.save(obj);
        return ResponseEntity.ok(updatedObj);
    }

    @PatchMapping("/v2/objects/{id}")
    public ResponseEntity<Object> updateObject(@PathVariable Integer id, @RequestBody ObjectDTO objectDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(this.objectService.update(id, objectDTO));
    }

    /**
     * @deprecated
     * This method is deprecated since the v2 API release.
     * <p> Use {@link ObjectController#removeAllObjects()} instead.
     */
    @DeleteMapping("/v1/objects/")
    @Deprecated(since = "v2")
    public ResponseEntity deleteAllObjects() {
        objectRepository.deleteAll();
        return status(HttpStatus.OK).build();
    }

    @DeleteMapping("/v2/objects/")
    public ResponseEntity<Void> removeAllObjects() {
        this.objectService.removeAll();
        return status(HttpStatus.OK).build();
    }

    /**
     * @deprecated
     * This method is deprecated since the v2 API release.
     * <p> Use {@link ObjectController#removeObjectById(Integer)} instead.
     */
    @DeleteMapping("/v1/objects/{id}")
    @Deprecated(since = "v2")
    public ResponseEntity deleteObject(@PathVariable Integer id) {
        objectRepository.deleteById(id);
        return status(HttpStatus.OK).build();
    }
    @DeleteMapping("/v2/objects/{id}")
    public ResponseEntity<Void> removeObjectById(@PathVariable Integer id) {
        this.objectService.removeById(id);
        return status(HttpStatus.OK).build();
    }
}
