package ru.itis.hateoaswell.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Method {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Date date;

    @ManyToMany(mappedBy = "methods")
    private List<Well> wells;
}
