package ru.itis.hateoaswell.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Layer {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Integer capacity;

    @ManyToOne
    @JoinColumn(name = "layer_id")
    private Horizon horizon;

    @ManyToMany(mappedBy = "layers")
    private List<Well> wells;

}
