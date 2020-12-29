package ru.itis.hateoaswell.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import ru.itis.hateoaswell.service.WellService;

@RepositoryRestController
public class WellController {

    @Autowired
    private WellService wellService;

    @RequestMapping(value = "/wells/{well-id}/close", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> closeWell(@PathVariable("well-id") Long wellId) {
        return ResponseEntity.ok(EntityModel.of(wellService.closeWell(wellId)));
    }
}
