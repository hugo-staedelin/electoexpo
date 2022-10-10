package fr.hstaedelin.electoexpo.services.mappers;

import fr.hstaedelin.electoexpo.models.dto.MuseumDTO;
import fr.hstaedelin.electoexpo.models.job.Museum;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface MuseumMapper {
    MuseumDTO map(Museum museum);
    Iterable<MuseumDTO> museumList(Iterable<Museum> museums);
    Museum map(MuseumDTO museumdto);
}
