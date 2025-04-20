package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.models.Journals;
import net.engineeringdigest.journalApp.repository.JornalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Jornalservice {

    @Autowired
    private JornalRepository journalrepository;

    public void addJornals(List<Journals> journals){
        for(Journals journal: journals){
            journalrepository.save(journal);
        }
    }

    public List<Journals> getall(){
        return journalrepository.findAll();
    }

    public Journals getById(String id) {
        return journalrepository.findById(id).orElse(null);
    }

}
