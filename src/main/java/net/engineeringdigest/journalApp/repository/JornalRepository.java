package net.engineeringdigest.journalApp.repository;

import net.engineeringdigest.journalApp.models.Journals;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JornalRepository extends MongoRepository<Journals,String> {

}
