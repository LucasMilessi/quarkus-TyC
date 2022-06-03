package co.com.sofka.service;

import co.com.sofka.model.TerminosCondiciones;
import co.com.sofka.repository.TerminosCondicionesRepository;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.LocalDate;

@ApplicationScoped
public class TerminoCondicionesService {

    @Inject
    TerminosCondicionesRepository terminosCondicionesRepository;

    public Uni<TerminosCondiciones> agregarTerminoCondicion(TerminosCondiciones terminosCondiciones){
        Integer cantidad = Integer.parseInt(terminosCondicionesRepository.findAll().count().toString());

                Uni<TerminosCondiciones> tyc = (Uni<TerminosCondiciones>) new TerminosCondiciones(
                        terminosCondiciones.getDescripcion(),
                        cantidad + 1, LocalDate.now());
        return tyc;
    }

    public Uni<TerminosCondiciones> ultimoTyC(){

        return terminosCondicionesRepository.findAll()
                .list()
                .map(tyc -> tyc
                        .get(tyc.size()-1));
    }
}
