package co.com.sofka.service;

import co.com.sofka.repository.TerminosCondicionesAceptacionRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class TerminosCondicionesAceptacionService {

    @Inject
    TerminosCondicionesAceptacionRepository terminosCondicionesAceptacionRepository;



}
