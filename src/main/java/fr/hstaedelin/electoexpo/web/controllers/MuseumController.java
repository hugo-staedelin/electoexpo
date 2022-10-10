package fr.hstaedelin.electoexpo.web.controllers;

import fr.hstaedelin.electoexpo.models.dto.MuseumDTO;
import fr.hstaedelin.electoexpo.models.job.Museum;
import fr.hstaedelin.electoexpo.services.MuseumService;
import fr.hstaedelin.electoexpo.services.mappers.MuseumMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static org.springframework.http.ResponseEntity.status;

@RestController
@CrossOrigin
@Slf4j
public class MuseumController {
    MuseumMapper museumMapper;
    MuseumService museumService;

    @Autowired
    public MuseumController(MuseumService museumService, MuseumMapper museumMapper) {
        this.museumService = museumService;
        this.museumMapper = museumMapper;
    }

    @GetMapping("/v2/museums/")
    public Iterable<MuseumDTO> findAllMuseums() {
        return this.museumService.getMuseums();
    }

    @GetMapping("/v2/museums/{id}")
    public Optional<MuseumDTO> findMuseumById(@PathVariable Integer id) {
        return this.museumService.getMuseumByID(id);
    }

    @PostMapping("/v2/museums/")
    public ResponseEntity<MuseumDTO> createMuseum(@RequestBody MuseumDTO objectDTO) {
        Museum object = this.museumService.addMuseum(objectDTO);
        if (object == null) {
            return status(HttpStatus.NOT_IMPLEMENTED).build();
        } return ResponseEntity.status(HttpStatus.CREATED).body(this.museumMapper.map(object));
    }

    @PatchMapping("/v2/museums/{id}")
    public ResponseEntity<Museum> updateMuseum(@PathVariable Integer id, @RequestBody MuseumDTO objectDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(this.museumService.update(id, objectDTO));
    }

    @DeleteMapping("/v2/museums/")
    public ResponseEntity<Void> removeAllMuseums() {
        this.museumService.removeAll();
        return status(HttpStatus.OK).build();
    }

    @DeleteMapping("/v2/museums/{id}")
    public ResponseEntity<Void> removeMuseumById(@PathVariable Integer id) {
        this.museumService.removeById(id);
        return status(HttpStatus.OK).build();
    }
}
