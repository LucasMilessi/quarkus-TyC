package co.com.sofka.model;

import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.time.Instant;


@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@MongoEntity(collection = "termYcondAceptacion")
public class TerminosCondicionesAceptacion {

    public ObjectId _idAceptacion;
    private String tipoDocumento;
    private String documento;
    private Integer versionTC;
    private Instant fechaDeAceptacion;

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public Integer getVersionTC() {
        return versionTC;
    }

    public void setVersionTC(Integer versionTC) {
        this.versionTC = versionTC;
    }

    public Instant getFechaDeAceptacion() {
        return fechaDeAceptacion;
    }

    public void setFechaDeAceptacion(Instant fechaDeAceptacion) {
        this.fechaDeAceptacion = fechaDeAceptacion;
    }
}
