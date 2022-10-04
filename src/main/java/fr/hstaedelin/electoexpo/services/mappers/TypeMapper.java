package fr.hstaedelin.electoexpo.services.mappers;

import fr.hstaedelin.electoexpo.models.dto.TypeDTO;
import fr.hstaedelin.electoexpo.models.job.Type;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface TypeMapper {
    TypeDTO map(Type type);
    Iterable<TypeDTO> typeList(Iterable<Type> type);
    Type map(TypeDTO typeDTO);
}
