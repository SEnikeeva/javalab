package ru.itis.hateoaswell.config;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import ru.itis.hateoaswell.controller.WellController;
import ru.itis.hateoaswell.enums.State;
import ru.itis.hateoaswell.model.Well;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class WellRepresentationProcessor  implements RepresentationModelProcessor<EntityModel<Well>> {
    @Override
    public EntityModel<Well> process(EntityModel<Well> model) {
        Well well = model.getContent();
        if (well != null && well.getState().equals(State.MINING))  {
            model.add(linkTo(methodOn(WellController.class).closeWell(well.getId())).withRel("closeWell"));
        }
        return model;
    }
}
