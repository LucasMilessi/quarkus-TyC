package co.com.sofka.repository;

import co.com.sofka.model.TerminosCondiciones;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoRepositoryBase;
import io.smallrye.mutiny.Uni;
import org.bson.types.ObjectId;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TerminosCondicionesRepository implements ReactivePanacheMongoRepositoryBase<TerminosCondiciones, ObjectId> {

    public Uni<Integer> findAllTerms(){
        return  findAll().count().onItem().transform(size->size.intValue());
    }

}
