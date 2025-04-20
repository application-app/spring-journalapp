package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.models.Journals;
import net.engineeringdigest.journalApp.service.Jornalservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/journal")
@RestController
public class JounalController {

    @Autowired
    private Jornalservice jornalservice;

    @PostMapping("bulk")
    public ResponseEntity<String> addJounal(@RequestBody List<Journals> journals){
        try{
            this.jornalservice.addJornals(journals);
            return ResponseEntity.status(HttpStatus.OK).body("Succuessfuly Added!");
        }catch(Exception error){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add jornals");
        }
    }

    @GetMapping
    public ResponseEntity<List<Journals>> getAll(){
        try{
            List<Journals> list = this.jornalservice.getall();
            return ResponseEntity.status(HttpStatus.OK).body(list);
        }catch(Exception error){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/by-id")
    public ResponseEntity<Journals> getById(@RequestParam String id) {
        try {
            Journals journal = this.jornalservice.getById(id);
            if (journal != null) {
                return ResponseEntity.status(HttpStatus.OK).body(journal);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception error) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping
    public ResponseEntity<String> updateJournal(@RequestParam String id, @RequestBody Journals updatedJournal) {
        try {
            boolean updated = this.jornalservice.updateJournal(id, updatedJournal);
            if (updated) {
                return ResponseEntity.status(HttpStatus.OK).body("Journal updated successfully!");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Journal with ID " + id + " not found.");
            }
        } catch (Exception error) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update journal.");
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deleteJournal(@RequestParam String id) {
        try {
            boolean deleted = this.jornalservice.deleteJournal(id);
            if (deleted) {
                return ResponseEntity.status(HttpStatus.OK).body("Journal deleted successfully!");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Journal with ID " + id + " not found.");
            }
        } catch (Exception error) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete journal.");
        }
    }


}
