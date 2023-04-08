package lk.acpt.note_collector_app.repo;

import lk.acpt.note_collector_app.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NoteRepo extends JpaRepository<Note,Integer> {

    @Query("SELECT p FROM Note p WHERE p.title LIKE %?1%"
            + " OR p.dateTime LIKE %?1%")
    public List<Note> search(String keyword);

    @Query("SELECT p FROM Note p WHERE p.title LIKE %?1%"
            + " OR p.dateTime LIKE %?1%")
    public Note searchNoteByTitle(String keyword);
}
