package org.shazam.utils;

import lombok.Getter;
import lombok.Setter;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.thucydides.core.util.EnvironmentVariables;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

@Getter
@Setter

public class CargaPayload {

    public static String cargaArchivo(String rutaArchivo) {
        try {
            return new String (Files.readAllBytes(
                    Paths.get(rutaArchivo)
            )
            , StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("Error cargando el archivo " + rutaArchivo,e);
        }
    }

//    private final String rutaArchivo;
////    private String texto;
//
//    public CargaPayload(String rutaArchivo) {
//        this.rutaArchivo = rutaArchivo;
//    }

//    public String cargarArchivo(){
//        try{
//            this.texto = new String(Files.readAllBytes(Paths.get(rutaArchivo)));
//            return this.texto;
//        } catch (IOException e) {
//            throw new RuntimeException("Error leyendo archivo en: " + rutaArchivo, e);
//        }
//    }

}
