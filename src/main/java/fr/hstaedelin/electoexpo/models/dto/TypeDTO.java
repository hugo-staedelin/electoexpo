package fr.hstaedelin.electoexpo.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;

public class TypeDTO {
    @JsonProperty("id")
    private Integer id;

    @NotNull
    @JsonProperty("label")
    private String label;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
