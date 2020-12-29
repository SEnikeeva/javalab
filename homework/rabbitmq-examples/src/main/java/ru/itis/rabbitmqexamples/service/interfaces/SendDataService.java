package ru.itis.rabbitmqexamples.service.interfaces;


import ru.itis.rabbitmqexamples.Dto.InputDataDto;
import ru.itis.rabbitmqexamples.model.User;

public interface SendDataService {
    InputDataDto senData(InputDataDto user);
}
