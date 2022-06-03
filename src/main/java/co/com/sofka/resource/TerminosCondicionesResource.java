package co.com.sofka.resource;

import co.com.sofka.model.TerminosCondiciones;
import co.com.sofka.model.TerminosCondicionesAceptacion;
import co.com.sofka.repository.TerminosCondicionesAceptacionRepository;
import co.com.sofka.repository.TerminosCondicionesRepository;
import co.com.sofka.service.TerminoCondicionesService;
import co.com.sofka.service.TerminosCondicionesAceptacionService;
import io.smallrye.mutiny.Uni;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.NOT_ACCEPTABLE;

@Path("/api/termYcond")
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
public class TerminosCondicionesResource {

    @Inject
    TerminoCondicionesService terminoCondicionesService;

    @Inject
    TerminosCondicionesAceptacionService terminosCondicionesAceptacionService;

    @POST
    public Uni<Response> crearTyC(TerminosCondiciones terminosCondiciones) {
        return terminoCondicionesService.agregarTerminoCondicion(terminosCondiciones)
                .map(termsConditions ->Response.ok(termsConditions).build());
    }

    @GET
    @Path("/mostrarTyC")
    @Produces(APPLICATION_JSON)
    public Uni<Response> mostrarTyC(){
        return terminoCondicionesService.ultimoTyC()
                .map(termsConditions -> Response.ok(termsConditions).build());
    }

    @POST
    @Path("/agree")
    @Produces(APPLICATION_JSON)
    public Uni<Response> crearAcepTyC(TerminosCondicionesAceptacion terminosCondicionesAceptacion) {
        if(terminosCondicionesAceptacion.getTipoDocumento().equals("Cedula")||
                terminosCondicionesAceptacion.getTipoDocumento().equals("Pasaporte")){

            return terminosCondicionesAceptacionService.agregarTyCaceptacion(terminosCondicionesAceptacion)
                    .map(acepTerm -> Response.ok(acepTerm).build())
                    .onFailure().
                    recoverWithItem(() -> Response.status(NOT_ACCEPTABLE).build());
        }
        return Uni.createFrom().item(Response.status(NOT_ACCEPTABLE).build());
    }
}
