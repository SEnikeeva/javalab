package jpa;

import jpa.config.RepositoriesConfig;
import jpa.repository.WellRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import jpa.model.Type;
import jpa.model.Well;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(RepositoriesConfig.class);
        WellRepository wellRepository = context.getBean(WellRepository.class);

        Well well = Well.builder()
                .name("10")
                .field("almet")
                .type(Type.liquidated)
                .build();

//        wellRepository.save(well);

        System.out.println(wellRepository.find(Type.delivery,"\\d{2}"));

    }
}
