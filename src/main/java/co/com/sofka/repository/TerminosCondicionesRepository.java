package co.com.sofka.repository;

import co.com.sofka.model.TerminosCondiciones;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoRepositoryBase;
import org.bson.types.ObjectId;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TerminosCondicionesRepository implements ReactivePanacheMongoRepositoryBase<TerminosCondiciones, ObjectId> {

}
