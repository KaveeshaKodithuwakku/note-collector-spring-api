package lk.acpt.note_collector_app.repo;

import lk.acpt.note_collector_app.model.Note;
import lk.acpt.note_collector_app.model.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ReminderRepo extends JpaRepository<Reminder,Integer> {


}
