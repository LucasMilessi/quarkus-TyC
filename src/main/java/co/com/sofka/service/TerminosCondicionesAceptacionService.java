package co.com.sofka.service;

import co.com.sofka.model.TerminosCondicionesAceptacion;
import co.com.sofka.repository.TerminosCondicionesAceptacionRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import io.smallrye.mutiny.Uni;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ApplicationScoped
public class TerminosCondicionesAceptacionService {
    @Inject
    TerminosCondicionesAceptacionRepository terminosCondicionesAceptacionRepository;

    private final Logger log = LoggerFactory.getLogger(TerminosCondicionesAceptacionService.class);

    public Uni<Object> agregarTyCaceptacion(TerminosCondicionesAceptacion terminosCondicionesAceptacion){

        return Uni.createFrom().item(terminosCondicionesAceptacion)
                .flatMap(aceptTyC->{
                    aceptTyC.setFechaDeAceptacion(LocalDate.now());
                    if (aceptTyC.getTipoDocumento().equals("Pasaporte")){
                        aceptTyC.setFechaDeAceptacion(LocalDate.now());
                        return agregarDocumentoPas(aceptTyC);
                    }
                    return agregarDocumentoCI(aceptTyC);
                });
    }

    public Uni<Object> agregarDocumentoCI(TerminosCondicionesAceptacion terminosCondicionesAceptacion){
        Pattern patron = Pattern.compile("[0-9]{2}-PN-[0-9]{3}-[0-9]{4}");
        Matcher matcher = patron.matcher(terminosCondicionesAceptacion.getDocumento());
        Boolean validar = matcher.matches();

        if (validar){
            return Uni.createFrom()
                    .item(terminosCondicionesAceptacion)
                    .flatMap(terminosCondicionesAceptacionRepository::persist)
                    .map(termCI -> "Se acepto el termino y condición para el documento "+terminosCondicionesAceptacion.getDocumento());
        }
        throw new IllegalArgumentException("El formato de CI ingresado es incorrecto");
    }
    public Uni<Object> agregarDocumentoPas(TerminosCondicionesAceptacion terminosCondicionesAceptacion){
        Pattern patron = Pattern.compile("[a-zA-Z0-9-]{5,16}");
        Matcher matcher = patron.matcher(terminosCondicionesAceptacion.getDocumento());
        Boolean validar = matcher.matches();

        if (validar){
            return Uni.createFrom()
                    .item(terminosCondicionesAceptacion)
                    .flatMap(terminosCondicionesAceptacionRepository::persist)
                    .map(termPas -> "Se acepto el termino y condición para el documento "+terminosCondicionesAceptacion.getDocumento());
        }
        throw new IllegalArgumentException("El formato de pasaporte ingresado es incorrecto");
    }
}
