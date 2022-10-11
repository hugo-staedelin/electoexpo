package fr.hstaedelin.electoexpo.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExpositionDTO {
    @JsonProperty("id")
    private Integer id;

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
