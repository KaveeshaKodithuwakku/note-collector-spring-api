package lk.acpt.note_collector_app.controller;

import lk.acpt.note_collector_app.dao.NoteDAO;
import lk.acpt.note_collector_app.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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
    @PostMapping("/save-notes")
    public Note saveNote(@RequestBody Note note){
        return noteDAO.save(note);
    }

    /*get all notes*/
    @GetMapping("/get-all")
    public List<Note> getAllNotes(){
        return noteDAO.findAll();
    }

    /*get notes by keyword*/
    @GetMapping("/search-note")
    public ResponseEntity<List<Note>> searchNotes(@RequestParam(value ="query",required = false) String keyword){

        List<Note> nte = noteDAO.listAll(keyword);

        if(nte==null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(nte);

    }


    /*get notes by noteId*/
    @GetMapping("/get-note/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable(value="id") Integer nteId){

        Note nte = noteDAO.findOne(nteId);

        if(nte==null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(nte);

    }


    /*update a note by note id*/
    @PutMapping("/update-note/{id}")
    public ResponseEntity<Note> updateNote(@PathVariable(value="id") Integer noteId,@RequestBody Note noteDetails){

        Note nte = noteDAO.findOne(noteId);

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

    /*Delete a note*/
    @DeleteMapping("/delete-note/{id}")
    public ResponseEntity<Note> deleteNote(@PathVariable(value="id") Integer noteId){

        Note nte = noteDAO.findOne(noteId);
        if(nte==null) {
            return ResponseEntity.notFound().build();
        }
        noteDAO.delete(nte);

        return ResponseEntity.ok().build();


    }



}
