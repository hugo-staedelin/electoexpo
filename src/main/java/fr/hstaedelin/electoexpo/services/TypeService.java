package fr.hstaedelin.electoexpo.services;

import fr.hstaedelin.electoexpo.configuration.exceptions.NotFoundException;
import fr.hstaedelin.electoexpo.models.dto.TypeDTO;
import fr.hstaedelin.electoexpo.models.job.Type;
import fr.hstaedelin.electoexpo.repositories.TypeRepository;
import fr.hstaedelin.electoexpo.services.mappers.TypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TypeService {
    private final TypeRepository typeRepository;
    private final TypeMapper typeMapper;

    @Autowired
    public TypeService(TypeRepository typeRepository, TypeMapper typeMapper) {
        this.typeRepository = typeRepository;
        this.typeMapper = typeMapper;
    }

    public Iterable<TypeDTO> getTypes() {
        return typeMapper.typeList(typeRepository.findAll());
    }

    public Optional<TypeDTO> getTypeByID(Integer id) {
        return Optional.of(typeMapper.map(typeRepository.findById(id).orElseThrow(NotFoundException::new)));
    }

    public Type addType(TypeDTO typeDTO) {
        Type type = typeMapper.map(typeDTO);
        typeRepository.save(type);
        return type;
    }

    public Type updateType(Integer id, TypeDTO typeDTO) {
        Optional<TypeDTO> type = this.getTypeByID(id);
        if (type.isPresent()) {
            typeRepository.save(this.update(typeDTO, this.typeMapper.map(type.get())));
            return this.typeMapper.map(type.get());
        } else return null;
    }

    private Type update(TypeDTO typeDTO, Type type) {
        type.setLabel(typeDTO.getLabel());
        return type;
    }

    public void removeAll() {
        typeRepository.deleteAll();
    }

    public void removeById(Integer id) {
        Optional<TypeDTO> type = this.getTypeByID(id);
        type.ifPresent(typeDTO -> typeRepository.delete(this.typeMapper.map(typeDTO)));
    }
}
