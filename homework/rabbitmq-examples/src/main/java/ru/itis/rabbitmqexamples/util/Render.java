package ru.itis.rabbitmqexamples.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.html2pdf.HtmlConverter;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Service;
import ru.itis.rabbitmqexamples.Dto.InputDataDto;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.UUID;

@Service
public class Render {
    private static final String OUTPUT_PATH = "output/";


    public static void render(String data, String direction, String course) {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_0);
        cfg.setDefaultEncoding("utf-8");
        try {
            Template template = cfg.getTemplate("src/main/resources/templates/" + direction + ".ftlh");
            StringWriter stringWriter = new StringWriter();
            InputDataDto user = new InputDataDto();
            try {
                user = new ObjectMapper().readValue(data, InputDataDto.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            HashMap<String, Object> map = new HashMap<>();
            map.put("user", user);
            map.put("course", course);
            template.process(map, stringWriter);
            HtmlConverter.convertToPdf(stringWriter.toString(), new FileOutputStream(OUTPUT_PATH +
                    course + "_" + user.getName() + UUID.randomUUID().toString().substring(0, 5) +".pdf"));

//            return stringWriter.toString();
        } catch (IOException | TemplateException e) {
            throw new IllegalArgumentException(e);
        }

    }
}
