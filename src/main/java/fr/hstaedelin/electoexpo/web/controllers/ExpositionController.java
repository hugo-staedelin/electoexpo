package fr.hstaedelin.electoexpo.web.controllers;

import fr.hstaedelin.electoexpo.models.dto.ExpositionDTO;
import fr.hstaedelin.electoexpo.models.job.Exposition;
import fr.hstaedelin.electoexpo.services.ExpositionService;
import fr.hstaedelin.electoexpo.services.mappers.ExpositionMapper;
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
public class ExpositionController {
    ExpositionMapper expositionMapper;
    ExpositionService expositionService;

    @Autowired
    public ExpositionController(ExpositionService expositionService, ExpositionMapper expositionMapper) {
        this.expositionService = expositionService;
        this.expositionMapper = expositionMapper;
    }

    @GetMapping("/v2/exposition/")
    public Iterable<ExpositionDTO> findAllExpositions() {
        return this.expositionService.getExpositions();
    }

    @GetMapping("/v2/exposition/{id}")
    public Optional<ExpositionDTO> findExpositionById(@PathVariable Integer id) {
        return this.expositionService.getExpositionByID(id);
    }

    @PostMapping("/v2/exposition/")
    public ResponseEntity<ExpositionDTO> createExposition(@RequestBody ExpositionDTO expositionDTO) {
        Exposition exposition = this.expositionService.addExposition(expositionDTO);
        if (exposition == null) {
            return status(HttpStatus.NOT_IMPLEMENTED).build();
        } return ResponseEntity.status(HttpStatus.CREATED).body(this.expositionMapper.map(exposition));
    }

    @PatchMapping("/v2/exposition/{id}")
    public ResponseEntity<Exposition> updateExposition(@PathVariable Integer id, @RequestBody ExpositionDTO expositionDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(this.expositionService.update(id, expositionDTO));
    }

    @DeleteMapping("/v2/exposition/")
    public ResponseEntity<Void> removeAllExpositions() {
        this.expositionService.removeAll();
        return status(HttpStatus.OK).build();
    }

    @DeleteMapping("/v2/exposition/{id}")
    public ResponseEntity<Void> removeExpositionById(@PathVariable Integer id) {
        this.expositionService.removeById(id);
        return status(HttpStatus.OK).build();
    }
}
