package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.types.Journals;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RequestMapping("/journal")
@RestController
public class JounalController {

    private Map<Long, Journals> myJournals = new HashMap<>();

    // Dummy data for testing
    public JounalController() {
        Journals j1 = new Journals();
        j1.setId(1L);
        j1.setName("Morning Reflection");
        j1.setMood("Happy");

        Journals j2 = new Journals();
        j2.setId(2L);
        j2.setName("Evening Thoughts");
        j2.setMood("Calm");

        myJournals.put(j1.getId(), j1);
        myJournals.put(j2.getId(), j2);
    }

    @GetMapping
    public List<Journals> getJournals() {
        return new ArrayList<>(myJournals.values());
    }

    @PostMapping
    public ResponseEntity<String> postJournals(@RequestBody Journals journal){
        try{
            this.myJournals.put(journal.getId(),journal);
            return ResponseEntity.ok("Journal added successfully!");
        }catch(Exception error){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add a journal");
        }
    }

    @PostMapping("/bulk")
    public ResponseEntity<String> postMultipleJournals(@RequestBody List<Journals> journalsList) {
        try {
            for (Journals journal : journalsList) {
                this.myJournals.put(journal.getId(), journal);
            }
            return ResponseEntity.ok("All journals added successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding journals.");
        }
    }

    @GetMapping("id/{journalId}")
    public Journals getJornalById(@PathVariable Long journalId){
        return myJournals.get(journalId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateJournal(@PathVariable Long id, @RequestBody Journals updatedJournal) {
        if (!myJournals.containsKey(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Journal not found");
        }

        updatedJournal.setId(id); // Ensure ID remains same
        myJournals.put(id, updatedJournal);

        return ResponseEntity.ok("Journal updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJournal(@PathVariable Long id) {
        if (myJournals.remove(id) != null) {
            return ResponseEntity.ok("Journal deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Journal not found");
        }
    }


}
