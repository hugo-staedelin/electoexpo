package fr.hstaedelin.electoexpo.models.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class ObjectDTO {
    private String name;
    private String description;
    private Integer period;
    private LocalDate dateOfBorrowing;
    private LocalDate endDateOfBorrowing;
    private Set<TypeDTO> types;
}
