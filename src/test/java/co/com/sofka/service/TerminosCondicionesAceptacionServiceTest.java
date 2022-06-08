package co.com.sofka.service;

import co.com.sofka.model.TerminosCondiciones;
import co.com.sofka.model.TerminosCondicionesAceptacion;
import co.com.sofka.repository.TerminosCondicionesAceptacionRepository;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import io.smallrye.mutiny.Uni;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class TerminosCondicionesAceptacionServiceTest {

    @InjectMock
    TerminosCondicionesAceptacionRepository terminosCondicionesAceptacionRepository;

    @InjectMock
    TerminosCondicionesAceptacionService terminosCondicionesAceptacionService;

    private TerminosCondicionesAceptacion tycAceptCI;
    private TerminosCondicionesAceptacion tycAceptP;

    private final String tipoDocumentoC = "Cedula";
    private final String tipoDocumentoP = "Pasaporte";

    private final String documentoC = "23-PN-234-4567";
    private final String documentoP = "23-EE-25";

    private final Integer versionTC = 3;

    private final LocalDate fechaAceptacion = LocalDate.now();

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);

        tycAceptCI = TerminosCondicionesAceptacion.builder()
                .tipoDocumento(tipoDocumentoC)
                .documento(documentoC)
                .versionTC(versionTC)
                .fechaDeAceptacion(fechaAceptacion)
                .build();

        tycAceptP = TerminosCondicionesAceptacion.builder()
                .tipoDocumento(tipoDocumentoP)
                .documento(documentoP)
                .versionTC(versionTC)
                .fechaDeAceptacion(fechaAceptacion)
                .build();

    }

    @Test
    void agregarTyCaceptacion() {
        Mockito.when(terminosCondicionesAceptacionRepository.persist(tycAceptCI))
                .thenReturn(Uni.createFrom()
                        .item(tycAceptCI));

        terminosCondicionesAceptacionService.agregarTyCaceptacion(tycAceptCI).subscribe().with(acep -> {
            assertNotNull(acep);
        });


    }

    @Test
    void agregarDocumentoCI() {
        Mockito.when(terminosCondicionesAceptacionRepository.persist(tycAceptCI))
                .thenReturn(Uni.createFrom()
                        .item(tycAceptCI));

        terminosCondicionesAceptacionService.agregarDocumentoCI(tycAceptCI).subscribe().with(ci -> {
                assertNotNull(ci);
        });
    }

    @Test
    void agregarDocumentoPas() {
        Mockito.when(terminosCondicionesAceptacionRepository.persist(tycAceptP))
                .thenReturn(Uni.createFrom()
                        .item(tycAceptP));

        terminosCondicionesAceptacionService.agregarDocumentoPas(tycAceptP).subscribe().with(pas -> {
            assertNotNull(pas);
        });
    }
}