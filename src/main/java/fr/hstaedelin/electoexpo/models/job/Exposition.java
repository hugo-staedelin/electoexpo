package fr.hstaedelin.electoexpo.models.job;

import javax.persistence.*;

@Entity
@Table(name = "Exposition")
public class Exposition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column()
    private Integer id;

    @Column()
    private String label;

    public Exposition() {}

    public Exposition(Integer id, String label) {
        this.id = id;
        this.label = label;
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
