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

    /* search special notes*/
    public List<Note> listAll(String keyword) {
        if (keyword != null) {
            return noteRepo.search(keyword);
        }
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

    /*update only isFavorite a note  */

    public void updateIsFavorite(Note note,boolean status) {
        noteRepo.updateFavoriteStatus(note.getNoteId(),status);
    }

    /* get favorite all notes*/
    public List<Note> getFavoriteNotes(String uId){
        return noteRepo.getFavoriteNotes(uId);
    }

    /* get notes by userid*/
    public List<Note> getNotesByUserId(String uId){
        return noteRepo.getNotesByUserId(uId);
    }



}
