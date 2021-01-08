package jpa.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Field {

    @Id
    private String _id;

    private String name;

    private List<String> wells;

    private List<String> methods;

}
