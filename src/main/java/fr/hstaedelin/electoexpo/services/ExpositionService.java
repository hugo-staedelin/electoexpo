package fr.hstaedelin.electoexpo.services;

import fr.hstaedelin.electoexpo.configuration.exceptions.NotFoundException;
import fr.hstaedelin.electoexpo.models.dto.ExpositionDTO;
import fr.hstaedelin.electoexpo.models.job.Exposition;
import fr.hstaedelin.electoexpo.repositories.ExpositionRepository;
import fr.hstaedelin.electoexpo.services.mappers.ExpositionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExpositionService {
    private final ExpositionRepository expositionRepository;
    private final ExpositionMapper expositionMapper;

    @Autowired
    public ExpositionService(ExpositionRepository expositionRepository, ExpositionMapper expositionMapper) {
        this.expositionRepository = expositionRepository;
        this.expositionMapper = expositionMapper;
    }

    public Iterable<ExpositionDTO> getExpositions() {
        return expositionMapper.expositionList(expositionRepository.findAll());
    }

    public Optional<ExpositionDTO> getExpositionByID(Integer id) {
        return Optional.of(expositionMapper.map(expositionRepository.findById(id).orElseThrow(NotFoundException::new)));
    }

    public Exposition addExposition(ExpositionDTO expositionDTO) {
        Exposition exposition = expositionMapper.map(expositionDTO);
        expositionRepository.save(exposition);
        return exposition;
    }

    public Exposition update(Integer id, ExpositionDTO expositionDTO) {
        Optional<ExpositionDTO> exposition = this.getExpositionByID(id);
        if (exposition.isPresent()) {
            expositionRepository.save(this.update(expositionDTO, this.expositionMapper.map(exposition.get())));
            return this.expositionMapper.map(exposition.get());
        } else return null;
    }

    private Exposition update(ExpositionDTO expositionDTO, Exposition exposition) {
        exposition.setLabel(expositionDTO.getLabel());
        return exposition;
    }

    public void removeAll() {
        expositionRepository.deleteAll();
    }

    public void removeById(Integer id) {
        Optional<ExpositionDTO> exposition = this.getExpositionByID(id);
        exposition.ifPresent(expositionDTO -> expositionRepository.delete(this.expositionMapper.map(expositionDTO)));
    }
}
