package fr.hstaedelin.electoexpo.web.controllers;

import fr.hstaedelin.electoexpo.models.dto.ObjectDTO;
import fr.hstaedelin.electoexpo.models.job.Object;
import fr.hstaedelin.electoexpo.models.job.Type;
import fr.hstaedelin.electoexpo.repositories.ObjectRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;
import java.util.Set;

@RestController
@CrossOrigin
@Slf4j
public class ObjectController {
    @Autowired
    ObjectRepository objectRepository;

    @GetMapping("/v1/objects/")
    public Iterable<Object> getAllObjects() {
        return objectRepository.findAll();
    }

    @GetMapping("/v1/objects/{id}")
    public Optional<Object> getObjectById(@PathVariable Integer id) {
        return objectRepository.findById(id);
    }

    @PostMapping("/v1/objects/")
    public ResponseEntity<Object> addObject(@RequestBody ObjectDTO objectDTO) {
        Object type = objectRepository.save(new Object(objectDTO));
        if (type == null) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
        } return new ResponseEntity<Object>(type, HttpStatus.CREATED);
    }

    @PatchMapping("/v1/objects/{id}")
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

    @DeleteMapping("/v1/objects/")
    public ResponseEntity deleteAllObjects() {
        objectRepository.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/v1/objects/{id}")
    public ResponseEntity deleteObject(@PathVariable Integer id) {
        objectRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
