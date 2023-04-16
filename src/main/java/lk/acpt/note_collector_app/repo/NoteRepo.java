package lk.acpt.note_collector_app.repo;

import lk.acpt.note_collector_app.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface NoteRepo extends JpaRepository<Note,Integer> {

    // --- search by id
    @Query("SELECT p FROM Note p WHERE p.title LIKE %?1%"
            + " OR p.dateTime LIKE %?1%")
    public List<Note> search(String keyword);

    @Query("SELECT p FROM Note p WHERE p.title LIKE %?1%"
            + " OR p.dateTime LIKE %?1%")
    public Note searchNoteByTitle(String keyword);

    //------ update
    @Modifying
    @Transactional
    @Query("UPDATE Note c SET c.isFavorite = :favStatus WHERE c.noteId = :noteId")
    int updateFavoriteStatus( int noteId,  boolean favStatus);


    //select only favorite notes -------
    @Query(value = "SELECT * FROM Note WHERE is_favorite = 1",nativeQuery = true)
    List<Note> getFavoriteNotes();


}
