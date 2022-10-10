package fr.hstaedelin.electoexpo.services.mappers;

import fr.hstaedelin.electoexpo.models.dto.ObjectDTO;
import fr.hstaedelin.electoexpo.models.job.Object;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface ObjectMapper {
    ObjectDTO map(Object object);
    Iterable<ObjectDTO> objectList(Iterable<Object> objects);
    Object map(ObjectDTO objectDTO);
}
