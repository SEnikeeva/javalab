package ru.itis.hateoaswell.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Measurement {
    @Id
    @GeneratedValue
    private Long id;

    private Date date;

    private String debit;

    @ManyToOne
    @JoinColumn(name = "measurement_id")
    private Well well;
}
