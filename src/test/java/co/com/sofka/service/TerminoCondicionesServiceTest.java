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

        TerminosCondiciones tyc2 = TerminosCondiciones.builder()
                .descripcion(descripcion)
                .version(version)
                .fechaDeCreacion(fecha)
                .build();

        Mockito.when(terminosCondicionesRepository.findAllTerms()).thenReturn(Uni.createFrom().item(1));
        Mockito.when(terminosCondicionesRepository.persist(tyc)).thenReturn(Uni.createFrom().item(tyc));

        terminoCondicionesService.agregarTerminoCondicion(tyc2).subscribe().with(termYcond -> {
            Assertions.assertEquals("descripcion", termYcond.getDescripcion());
        });
    }

    @Test
    void mostrarVersion() {
        ObjectId objectId1 = new ObjectId("2");

        List<TerminosCondiciones> listTyC = new ArrayList<>();
        listTyC.add(new TerminosCondiciones(objectId, descripcion, version, fecha));
        listTyC.add(new TerminosCondiciones(objectId1, "descripcion2",2,fecha));


    }

    @Test
    void ultimoTyC() {

        ObjectId objectId1 = new ObjectId("62a0c1034a8d71536568fdf5");

        List<TerminosCondiciones> listTyC = new ArrayList<>();
        listTyC.add(new TerminosCondiciones(objectId, descripcion, version, fecha));
        listTyC.add(new TerminosCondiciones(objectId1, "descripcion2",2,fecha));

        Mockito.when(terminosCondicionesRepository.findAll().list()).thenReturn(Uni.createFrom().item(listTyC));

        terminoCondicionesService.ultimoTyC().subscribe().with(ultVer ->
                Assertions.assertEquals(2, ultVer.getVersion()));

    }
}