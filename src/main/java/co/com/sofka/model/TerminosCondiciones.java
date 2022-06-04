package co.com.sofka.model;

import io.quarkus.mongodb.panache.common.MongoEntity;
import io.smallrye.common.constraint.NotNull;
import org.bson.types.ObjectId;

import java.time.LocalDate;

@MongoEntity(collection = "termYcond")
public class TerminosCondiciones {

    private ObjectId _id;
    @NotNull
    private String descripcion;
    private Integer version;
    private LocalDate fechaDeCreacion;

    public TerminosCondiciones() {
    }

    public TerminosCondiciones(String descripcion, Integer version) {
        this.descripcion = descripcion;
        this.version = version;
        this.fechaDeCreacion = LocalDate.now();
    }

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public LocalDate getFechaDeCreacion() {
        return fechaDeCreacion;
    }

    public void setFechaDeCreacion(LocalDate fechaDeCreacion) {
        this.fechaDeCreacion = fechaDeCreacion;
    }
}
