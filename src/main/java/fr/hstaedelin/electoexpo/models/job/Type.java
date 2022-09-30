package fr.hstaedelin.electoexpo.models.job;

import fr.hstaedelin.electoexpo.models.dto.TypeDTO;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Type")
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column()
    private Integer id;
    @Column()
    private String label;

    public Type() {}

    public Type(Integer id, String label) {
        this.id = id;
        this.label = label;
    }

    public Type(TypeDTO typeDTO) {
        this.label = typeDTO.getLabel();
    }

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
