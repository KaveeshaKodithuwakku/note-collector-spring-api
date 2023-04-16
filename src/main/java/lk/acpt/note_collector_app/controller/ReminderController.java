package lk.acpt.note_collector_app.controller;

import lk.acpt.note_collector_app.dao.NoteDAO;
import lk.acpt.note_collector_app.dao.ReminderDAO;
import lk.acpt.note_collector_app.model.Note;
import lk.acpt.note_collector_app.model.Reminder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/reminder")
public class ReminderController {

    @Autowired
    ReminderDAO reminderDAO;


    /*save reminder*/
    @PostMapping("/save-reminder")
    public Reminder saveReminder(@RequestBody Reminder reminder){
        return reminderDAO.save(reminder);
    }

    /*get all reminders*/
    @GetMapping("/get-all-reminders")
    public List<Reminder> getReminders(){
        return reminderDAO.findAll();
    }



    /*get reminder by reminder id*/
    @GetMapping("/get-reminder/{id}")
    public ResponseEntity<Reminder> getReminderById(@PathVariable(value="id") Integer reminderId){

        Reminder reminder = reminderDAO.findOne(reminderId);

        if(reminder==null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(reminder);

    }


    /*update a note by note id*/
    @PutMapping("/update-reminder/{id}")
    public ResponseEntity<Reminder> updateReminder(@PathVariable(value="id") Integer noteId,@RequestBody Reminder reminderDetails){

        Reminder reminder = reminderDAO.findOne(noteId);

        if(reminder==null) {
            return ResponseEntity.notFound().build();
        }

        reminder.setTitle(reminderDetails.getTitle());
        reminder.setDescription(reminderDetails.getDescription());
        reminder.setDate(reminderDetails.getDate());
        reminder.setTime(reminderDetails.getTime());


        Reminder updateReminder = reminderDAO.save(reminder);
        return ResponseEntity.ok().body(updateReminder);
    }

    /*Delete a note*/
    @DeleteMapping("/delete-reminder/{id}")
    public ResponseEntity<Reminder> deleteNote(@PathVariable(value="id") Integer reminderId){

        Reminder reminder = reminderDAO.findOne(reminderId);
        if(reminder==null) {
            return ResponseEntity.notFound().build();
        }
        reminderDAO.delete(reminder);

        return ResponseEntity.ok().build();


    }



}
