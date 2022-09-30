package fr.hstaedelin.electoexpo.web.controllers;

import fr.hstaedelin.electoexpo.models.dto.TypeDTO;
import fr.hstaedelin.electoexpo.models.job.Type;
import fr.hstaedelin.electoexpo.repositories.TypeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;

@RestController
@CrossOrigin
@Slf4j
public class TypeController {
    @Autowired
    TypeRepository typeRepository;

    @GetMapping("/objects/types/v1/")
    public Iterable<Type> getAllTypes() {
        return typeRepository.findAll();
    }

    @GetMapping("/objects/types/v1/{id}")
    public Optional<Type> getTypeById(@PathVariable Integer id) {
        return typeRepository.findById(id);
    }

    @PostMapping("/objects/types/v1/")
    public ResponseEntity<Type> addType(@RequestBody TypeDTO typeDTO) {
        Type type = typeRepository.save(new Type(typeDTO));
        if (type == null) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
        } return new ResponseEntity<Type>(type, HttpStatus.CREATED);
    }

    @PatchMapping("/objects/types/v1/{id}")
    public ResponseEntity<Type> editType(@PathVariable Integer id, @RequestBody TypeDTO typeDTO) throws IOException {
        Type obj = typeRepository.findById(id).orElseThrow(IOException::new);
        obj.setLabel(typeDTO.getLabel());
        typeRepository.save(obj);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/objects/types/v1/")
    public ResponseEntity deleteAllTypes() {
        typeRepository.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/objects/types/v1/{id}")
    public ResponseEntity deleteType(@PathVariable Integer id) {
        typeRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
