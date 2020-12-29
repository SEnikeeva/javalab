package ru.itis.hateoaswell.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.hateoaswell.enums.State;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Well {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "well_id")
    private Field field;

    @ManyToMany
    @JoinTable(name = "well_layer",
            joinColumns = @JoinColumn(name = "well_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "layer_id", referencedColumnName = "id"))
    List<Layer> layers;

    @ManyToMany
    @JoinTable(name = "well_method",
            joinColumns = @JoinColumn(name = "well_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "method_id", referencedColumnName = "id"))
    List<Method> methods;

    @Enumerated(value = EnumType.STRING)
    private State state;

    @OneToMany(mappedBy = "well")
    private List<Measurement> measurements;

    public void closeWell() {
        if (state.equals(State.MINING)) {
            state = State.LIQUIDATED;
        } else {
            throw new IllegalStateException();
        }
    }

}
