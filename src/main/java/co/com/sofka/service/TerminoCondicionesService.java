package co.com.sofka.service;

import co.com.sofka.model.TerminosCondiciones;
import co.com.sofka.repository.TerminosCondicionesRepository;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class TerminoCondicionesService {

    @Inject
    TerminosCondicionesRepository terminosCondicionesRepository;

    public Uni<TerminosCondiciones> agregarTerminoCondicion(TerminosCondiciones terminosCondiciones){

        return terminosCondicionesRepository.findAllTerms().map(tyc ->
                new TerminosCondiciones(terminosCondiciones.getDescripcion(), tyc.intValue() +1))
                .flatMap(terminosCondicionesRepository::persist);


    }

    public Uni<TerminosCondiciones> ultimoTyC(){

        return terminosCondicionesRepository.findAll()
                .list()
                .map(tyc -> tyc
                        .get(tyc.size()-1));
    }
}
