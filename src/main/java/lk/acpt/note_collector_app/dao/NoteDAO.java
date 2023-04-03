package lk.acpt.note_collector_app.dao;

import lk.acpt.note_collector_app.model.Note;
import lk.acpt.note_collector_app.repo.NoteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteDAO {

    @Autowired
    NoteRepo noteRepo;

    /*to save a notes*/
    public Note save(Note nte) {
        return noteRepo.save(nte);
    }


    /* search all notes*/
    public List<Note> findAll(){
        return noteRepo.findAll();
    }


    /*get a note by id*/
    public Note findOne(Integer id) {
        return noteRepo.findById(id).get();
    }


    /*delete a note*/

    public void delete(Note note) {
        noteRepo.delete(note);
    }

}
