package lk.acpt.note_collector_app.controller;

import lk.acpt.note_collector_app.dao.NoteDAO;
import lk.acpt.note_collector_app.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/note")
public class NoteController {

    @Autowired
    NoteDAO noteDAO;


    /*save notes*/
    @PostMapping
    public Note saveStudent(@RequestBody Note note){
        return noteDAO.save(note);
    }

    /*get all notes*/
    @GetMapping("/")
    public List<Note> getAllNotes(){
        return noteDAO.findAll();
    }

    /*get notes by noteId*/
    @GetMapping("/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable(value="id") Integer nteId){

        Note nte = noteDAO.findOne(nteId);

        if(nte==null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(nte);

    }

    /*update a student by stdid*/
    @PutMapping("/{id}")
    public ResponseEntity<Note> updateNote(@PathVariable(value="id") Integer stdid,@RequestBody Note noteDetails){

        Note nte = noteDAO.findOne(stdid);

        if(nte==null) {
            return ResponseEntity.notFound().build();
        }

        nte.setTitle(noteDetails.getTitle());
        nte.setDescription(noteDetails.getDescription());
        nte.setDateTime(noteDetails.getDateTime());
        nte.setImage(noteDetails.getImage());

        Note updateNote = noteDAO.save(nte);
        return ResponseEntity.ok().body(updateNote);
    }

    /*Delete a student*/
    @DeleteMapping("/{id}")
    public ResponseEntity<Note> deleteNote(@PathVariable(value="id") Integer nteId){

        Note nte = noteDAO.findOne(nteId);
        if(nte==null) {
            return ResponseEntity.notFound().build();
        }
        noteDAO.delete(nte);

        return ResponseEntity.ok().build();


    }



}
