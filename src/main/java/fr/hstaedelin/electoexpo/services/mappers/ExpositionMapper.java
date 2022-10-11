package fr.hstaedelin.electoexpo.services.mappers;

import fr.hstaedelin.electoexpo.models.dto.ExpositionDTO;
import fr.hstaedelin.electoexpo.models.job.Exposition;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface ExpositionMapper {
    ExpositionDTO map(Exposition exposition);
    Iterable<ExpositionDTO> expositionList(Iterable<Exposition> expositions);
    Exposition map(ExpositionDTO expositionDTO);
}
