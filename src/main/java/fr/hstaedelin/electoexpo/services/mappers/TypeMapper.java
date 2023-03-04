package fr.hstaedelin.electoexpo.services.mappers;

import fr.hstaedelin.electoexpo.models.dto.TypeDTO;
import fr.hstaedelin.electoexpo.models.job.Type;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface TypeMapper {
    TypeDTO map(Type type);
    Iterable<TypeDTO> typeDtoList(Iterable<Type> type);
    Type map(TypeDTO typeDTO);
    Iterable<Type> typeList(Iterable<TypeDTO> typeDTO);
}
