package co.com.sofka.resource;

import co.com.sofka.model.TerminosCondiciones;
import co.com.sofka.service.TerminoCondicionesService;
import io.smallrye.mutiny.Uni;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;


import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/api/termYcond")
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
public class TerminosCondicionesResource {

    @Inject
    TerminoCondicionesService terminoCondicionesService;



    @POST
    @Produces(APPLICATION_JSON)
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

    @GET
    @Path("/mostrarTyC/{version}")
    @Produces(APPLICATION_JSON)
    public Uni<Response> mostrarTyCversion(Integer version){
        return terminoCondicionesService.mostrarVersion(version)
                .map(termsConditions -> Response.ok(termsConditions).build());
    }
}
