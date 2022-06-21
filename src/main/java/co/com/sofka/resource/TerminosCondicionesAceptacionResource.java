package co.com.sofka.resource;

import co.com.sofka.model.TerminosCondicionesAceptacion;
import co.com.sofka.service.TerminosCondicionesAceptacionService;
import io.smallrye.mutiny.Uni;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Variant;

import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.NOT_ACCEPTABLE;


@Path("/api/termYcond")
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
public class TerminosCondicionesAceptacionResource {

    @Inject
    TerminosCondicionesAceptacionService terminosCondicionesAceptacionService;

    @POST
    @Path("/crearAcept")
    @Consumes(APPLICATION_JSON)
    public Uni<Response> crearAcepTyC(TerminosCondicionesAceptacion terminosCondicionesAceptacion) {
        
            return terminosCondicionesAceptacionService.agregarTyCaceptacion(terminosCondicionesAceptacion)
                    .map(acepTerm -> Response.ok(acepTerm).build())
                    .onFailure()
                    .recoverWithItem(() -> Response.status(NOT_ACCEPTABLE).build());
        }
    }
