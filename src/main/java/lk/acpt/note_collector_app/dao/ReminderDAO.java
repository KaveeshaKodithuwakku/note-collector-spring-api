package lk.acpt.note_collector_app.dao;

import lk.acpt.note_collector_app.model.Note;
import lk.acpt.note_collector_app.model.Reminder;
import lk.acpt.note_collector_app.repo.NoteRepo;
import lk.acpt.note_collector_app.repo.ReminderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReminderDAO {

    @Autowired
    ReminderRepo reminderRepo;

    /*to save a reminder*/
    public Reminder save(Reminder reminder) {
        return reminderRepo.save(reminder);
    }


    /* search all reminders*/
    public List<Reminder> findAll(){
        return reminderRepo.findAll();
    }



    /*get a reminder by id*/
    public Reminder findOne(Integer id) {
        return reminderRepo.findById(id).get();
    }


    /*delete a reminder*/

    public void delete(Reminder reminder) {
        reminderRepo.delete(reminder);
    }




}
