package co.com.sofka.service;

import co.com.sofka.model.TerminosCondiciones;
import co.com.sofka.repository.TerminosCondicionesRepository;
import io.quarkus.test.Mock;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import io.smallrye.mutiny.Uni;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class TerminoCondicionesServiceTest {

    @InjectMock
    TerminoCondicionesService terminoCondicionesService;

    @InjectMock
    TerminosCondicionesRepository terminosCondicionesRepository;

    private TerminosCondiciones tyc;

    private final ObjectId objectId = new ObjectId("62a0c1034a8d71536568fdf4");
    private final String descripcion = "descripcion";
    private final Integer version = 1;
    private final LocalDate fecha = LocalDate.now();


    @BeforeEach
    void setUp(){

        MockitoAnnotations.openMocks(this);

        tyc = TerminosCondiciones.builder()
                .descripcion(descripcion)
                .version(version)
                .fechaDeCreacion(fecha)
                .build();
    }

    @Test
    void agregarTerminoCondicionTest() {

        Mockito.when(terminosCondicionesRepository.findAllTerms()).thenReturn(Uni.createFrom().item(1));
        Mockito.when(terminosCondicionesRepository.persist(tyc)).thenReturn(Uni.createFrom().item(tyc));

        terminoCondicionesService.agregarTerminoCondicion(tyc).subscribe().with(termYcond -> {
            assertEquals("62a0c1034a8d71536568fdf4", termYcond.id);
            assertEquals("descripcion", termYcond.getDescripcion());
            assertEquals(1, termYcond.getVersion());
            assertEquals(fecha, termYcond.getFechaDeCreacion());
        });
    }

    @Test
    void mostrarVersion() {

        Mockito.when(terminosCondicionesRepository.findByVersion(version)).thenReturn(Uni.createFrom().item(tyc));

        terminoCondicionesService.mostrarVersion(version).subscribe().with(version ->
                assertEquals(version, version.getVersion()));

    }

    @Test
    void ultimoTyC() {

        ObjectId objectId1 = new ObjectId("62a0c1034a8d71536568fdf5");
        String desc = "Termino y condicion";
        Integer ver = 2;

        List<TerminosCondiciones> listTyC = new ArrayList<>();
        listTyC.add(new TerminosCondiciones(objectId, descripcion, version, fecha));
        listTyC.add(new TerminosCondiciones(objectId1,desc,ver,fecha));


        Mockito.when(terminosCondicionesRepository.listAll()).thenReturn(Uni.createFrom().item(listTyC));

        terminoCondicionesService.ultimoTyC().subscribe().with(ultVer ->
                assertEquals(2, ultVer.getVersion()));
    }
}