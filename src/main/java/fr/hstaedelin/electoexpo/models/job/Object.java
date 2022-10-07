package fr.hstaedelin.electoexpo.models.job;

import fr.hstaedelin.electoexpo.models.dto.ObjectDTO;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "Object")
public class Object {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column()
    private Integer id;
    @Column()
    private String name;
    @Column()
    private String description;
    @Column()
    private Integer period;
    @Column()
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBorrowing;
    @Column()
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDateOfBorrowing;

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Set<Type> type;

    public Object() {}

    public Object(Integer id, String name, String description, int period, LocalDate dateOfBorrowing, LocalDate endDateOfBorrowing, Set<Type> type) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.period = period;
        this.dateOfBorrowing = dateOfBorrowing;
        this.endDateOfBorrowing = endDateOfBorrowing;
        this.type = type;
    }

    public Object(ObjectDTO objectDTO) {
        this.name = objectDTO.getName();
        this.description = objectDTO.getDescription();
        this.period = objectDTO.getPeriod();
        this.dateOfBorrowing = objectDTO.getDateOfBorrowing();
        this.endDateOfBorrowing = objectDTO.getEndDateOfBorrowing();
    }

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

    public Set<Type> getType() {
        return type;
    }

    public void setType(Set<Type> type) {
        this.type = type;
    }
}
