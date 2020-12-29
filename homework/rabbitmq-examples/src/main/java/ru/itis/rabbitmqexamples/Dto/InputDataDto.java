package ru.itis.rabbitmqexamples.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InputDataDto {
    private String name;
    private String surname;
    private Integer age;
    private String passport;
    private String operation;
}
