package fr.hstaedelin.electoexpo.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class ObjectDTO {
    @JsonProperty("id")
    private Integer id;

    @JsonProperty("name")
    @NotNull
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("period")
    @NotNull
    private Integer period;

    @JsonProperty("dateOfBorrowing")
    @NotNull
    private LocalDate dateOfBorrowing;

    @JsonProperty("endDateOfBorrowing")
    @NotNull
    private LocalDate endDateOfBorrowing;

    @JsonProperty("types")
    private List<TypeDTO> types;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public LocalDate getDateOfBorrowing() {
        return dateOfBorrowing;
    }

    public void setDateOfBorrowing(LocalDate dateOfBorrowing) {
        this.dateOfBorrowing = dateOfBorrowing;
    }

    public LocalDate getEndDateOfBorrowing() {
        return endDateOfBorrowing;
    }

    public void setEndDateOfBorrowing(LocalDate endDateOfBorrowing) {
        this.endDateOfBorrowing = endDateOfBorrowing;
    }

    public List<TypeDTO> getTypes() {
        return types;
    }

    public void setTypes(List<TypeDTO> types) {
        this.types = types;
    }
}
