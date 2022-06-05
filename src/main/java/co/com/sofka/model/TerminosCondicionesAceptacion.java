package co.com.sofka.model;

import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.types.ObjectId;

import java.time.LocalDate;

@MongoEntity(collection = "termYcondAceptacion")
public class TerminosCondicionesAceptacion {

    public ObjectId _idAceptacion;
    private String tipoDocumento;
    private String documento;
    private Integer versionTC;
    private LocalDate fechaDeAceptacion;

    public TerminosCondicionesAceptacion(String tipoDocumento, String documento, Integer versionTC, LocalDate fechaDeAceptacion) {
        this.tipoDocumento = tipoDocumento;
        this.documento = documento;
        this.versionTC = versionTC;
        this.fechaDeAceptacion = fechaDeAceptacion;
    }

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

    public LocalDate getFechaDeAceptacion() {
        return fechaDeAceptacion;
    }

    public void setFechaDeAceptacion(LocalDate fechaDeAceptacion) {
        this.fechaDeAceptacion = fechaDeAceptacion;
    }
}
