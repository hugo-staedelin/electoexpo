package fr.hstaedelin.electoexpo.web.controllers;

import fr.hstaedelin.electoexpo.models.dto.TypeDTO;
import fr.hstaedelin.electoexpo.models.job.Type;
import fr.hstaedelin.electoexpo.repositories.TypeRepository;
import fr.hstaedelin.electoexpo.services.TypeService;
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
    private final TypeService typeService;

//    @Autowired
    private final TypeRepository typeRepository;

    @Autowired
    public TypeController(TypeService typeService, TypeRepository typeRepository) {
        this.typeService = typeService;
        this.typeRepository = typeRepository;
    }

    @GetMapping("/v1/objects/types/")
    @Deprecated(since = "v2")
    /**
     * @deprecated
     * This method is deprecated since the rc of the new API version.
     * <p> Use {@link TypeController#findAllTypes()} instead.
     */
    public Iterable<Type> getAllTypes() {
        return typeRepository.findAll();
    }

    @GetMapping("/v2/objects/types/")
    public Iterable<TypeDTO> findAllTypes() {
        return this.typeService.getTypes();
    }

    /**
     * @deprecated
     * This method is deprecated since the rc of the new API version.
     * <p> Use {@link TypeController#findTypeById(Integer)} instead.
     */
    @GetMapping("/v1/objects/types/{id}")
    @Deprecated(since = "v2")
    public Optional<Type> getTypeById(@PathVariable Integer id) {
        return typeRepository.findById(id);
    }

    @GetMapping("/v2/objects/types/{id}")
    public Optional<TypeDTO> findTypeById(@PathVariable Integer id) {
        return typeService.getTypeByID(id);
    }

    /**
     * @deprecated
     * This method is deprecated since the rc of the new API version.
     * <p> Use {@link TypeController#addNewType(TypeDTO)} instead.
     */
    @PostMapping("/v1/objects/types/")
    @Deprecated(since = "v2")
    public ResponseEntity<Type> addType(@RequestBody TypeDTO typeDTO) {
        Type type = typeRepository.save(new Type(typeDTO));
        if (type == null) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
        } return new ResponseEntity<Type>(type, HttpStatus.CREATED);
    }

    @PostMapping("/v2/objects/types/")
    public ResponseEntity<Type> addNewType(@RequestBody TypeDTO typeDTO) {
        Type type = typeService.addType(typeDTO);
        if (type == null) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
        } return new ResponseEntity<Type>(type, HttpStatus.CREATED);
    }

    /**
     * @deprecated
     * This method is deprecated since the rc of the new API version.
     * <p> Use {@link TypeController#updateType(Integer, TypeDTO)}} instead.
     */
    @PatchMapping("/v1/objects/types/{id}")
    @Deprecated(since = "v2")
    public ResponseEntity<Type> editType(@PathVariable Integer id, @RequestBody TypeDTO typeDTO) throws IOException {
        Type obj = typeRepository.findById(id).orElseThrow(IOException::new);
        obj.setLabel(typeDTO.getLabel());
        typeRepository.save(obj);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PatchMapping("/v2/objects/types/{id}")
    public ResponseEntity<Type> updateType(@PathVariable Integer id, @RequestBody TypeDTO typeDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(this.typeService.updateType(id, typeDTO));
    }

    /**
     * @deprecated
     * This method is deprecated since the rc of the new API version.
     * <p> Use {@link TypeController#removeAllTypes()} instead.
     */
    @DeleteMapping("/v1/objects/types/")
    @Deprecated(since = "v2")
    public ResponseEntity deleteAllTypes() {
        typeRepository.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/v2/objects/types/")
    public ResponseEntity removeAllTypes() {
        this.typeService.removeAll();
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * @deprecated
     * This method is deprecated since the rc of the new API version.
     * <p> Use {@link TypeController#removeTypeById(Integer)} instead.
     */
    @DeleteMapping("/v1/objects/types/{id}")
    @Deprecated(since = "v2")
    public ResponseEntity deleteType(@PathVariable Integer id) {
        typeRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/v2/objects/types/{id}")
    public ResponseEntity removeTypeById(@PathVariable Integer id) {
        this.typeService.removeById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
