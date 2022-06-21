package co.com.sofka.service;

import co.com.sofka.model.TerminosCondiciones;
import co.com.sofka.repository.TerminosCondicionesRepository;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.*;

@ApplicationScoped
public class TerminoCondicionesService {

    @Inject
    TerminosCondicionesRepository terminosCondicionesRepository;

    public Uni<? extends TerminosCondiciones> agregarTerminoCondicion(TerminosCondiciones terminosCondiciones){

        return terminosCondicionesRepository.findAllTerms()
                .map(tyc -> contructor(terminosCondiciones.getDescripcion(),
                        tyc.intValue() + 1,
                        terminosCondiciones.getFechaDeCreacion()))
                .flatMap(terminosCondicionesRepository::persist);
    }

    private TerminosCondiciones contructor(String descripcion, Integer version, Instant fecha){

        fecha = ZonedDateTime.now().toInstant();

        return TerminosCondiciones.builder()
                .descripcion(descripcion)
                .version(version)
                .fechaDeCreacion(fecha)
                .build();
    }

    public Uni<TerminosCondiciones> mostrarVersion(Integer version){
        return terminosCondicionesRepository
                .findByVersion(version);
    }

    public Uni<TerminosCondiciones> ultimoTyC(){

        return terminosCondicionesRepository.findAll()
                .list()
                .map(tyc -> tyc
                        .get(tyc.size()-1));
    }
}
