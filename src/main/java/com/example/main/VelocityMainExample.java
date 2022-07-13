package com.example.main;

import com.example.model.Persona;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VelocityMainExample {
    public static void main(String[] args) {
        //Inicializar velocity
        VelocityEngine engine = new VelocityEngine();
        engine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        engine.setProperty("classpath.resource.loader.class",
                ClasspathResourceLoader.class.getName());
        engine.init();

        //Lista de personas
        List<Persona> personas = new ArrayList<Persona>();
        for (int i = 0; i<10; i++){
            Persona persona = new Persona();
            persona.setNombre("Persona " + i);
            persona.setEdad(18);
            personas.add(persona);
        }

        //Inicializar contexto de velocity
        VelocityContext context = new VelocityContext();
        context.put("title", "Pagina de prueba");
        context.put("personas", personas);

        //Obtener la plantilla
        Template template = engine.getTemplate("/templates/prueba.vm");

        //Obtener resultado
        /*StringWriter stringWriter = new StringWriter();
        template.merge(context, stringWriter);

        System.out.println(stringWriter.toString());*/
        try {
            FileWriter fileWriter = new FileWriter(new File("src/main/resources/outputs/prueba.html"));
            template.merge(context, fileWriter);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
