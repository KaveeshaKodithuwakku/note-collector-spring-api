package lk.acpt.note_collector_app.repo;

import lk.acpt.note_collector_app.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepo extends JpaRepository<Note,Integer> {
}
