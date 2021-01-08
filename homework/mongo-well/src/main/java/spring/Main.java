package spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import spring.config.SimpleMongoConfig;
import spring.model.Field;
import spring.model.Type;
import spring.model.Well;

import java.util.Collections;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;


public class Main {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SimpleMongoConfig.class);
        MongoTemplate mongoTemplate = applicationContext.getBean(MongoTemplate.class);

        // save
        Well well = Well.builder()
                .name("9")
                .field("roma")
                .type(Type.delivery)
                .build();

        Field field = Field.builder()
                .name("roma")
                .wells(Collections.singletonList("10"))
                .methods(Collections.singletonList("grp"))
                .build();

//        mongoTemplate.save(well, "well");
//        mongoTemplate.save(field, "field");


        // find
        List<Well> wells = mongoTemplate.find(new Query(
                where("type").is(Type.liquidated)
                    .orOperator(where("field").is("roma")
                            ,where("name").regex("\\d{2}")
                    )),
                Well.class, "well");
        for (Well w: wells) {
            System.out.println(w.getName());
        }

    }
}
