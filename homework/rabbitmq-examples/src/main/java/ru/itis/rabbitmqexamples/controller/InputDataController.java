package ru.itis.rabbitmqexamples.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.rabbitmqexamples.Dto.InputDataDto;
import ru.itis.rabbitmqexamples.model.User;
import ru.itis.rabbitmqexamples.service.interfaces.SendDataService;

@RestController
@RequiredArgsConstructor
public class InputDataController {

    private final SendDataService sendDataService;

    @PostMapping(value = {"/input"})
    public ResponseEntity<InputDataDto> getData(@RequestBody InputDataDto user) {
        return ResponseEntity.ok(sendDataService.senData(user));
    }
}
