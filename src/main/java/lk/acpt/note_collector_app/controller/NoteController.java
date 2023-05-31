package lk.acpt.note_collector_app.controller;

import lk.acpt.note_collector_app.dao.NoteDAO;
import lk.acpt.note_collector_app.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/note")
public class NoteController {

    private final String FOLDER_PATH= "F:\\Afsd\\Interview Projects\\File Uploads\\Images\\";

    @Autowired
    NoteDAO noteDAO;

    /*save notes*/
    @PostMapping("/save-notes")
    public Note saveNote(@RequestParam(value = "userId")String userId,@RequestParam(value = "title")String title, @RequestParam(value = "description") String description, @RequestParam(value = "dateTime") String dateTime, @RequestParam(value = "favorite") Boolean favorite,@RequestParam(value = "image") MultipartFile file) throws IOException {
        String filePath = FOLDER_PATH+file.getOriginalFilename();

        Note save = noteDAO.save(new Note(userId,title, description, dateTime, favorite,file.getOriginalFilename()));

        file.transferTo(new File(filePath));

        return save;
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<byte[]> downloadImage(@PathVariable String fileName) throws IOException {
        byte[] image = Files.readAllBytes(new File(FOLDER_PATH+fileName).toPath());
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(image);
    }

    /*save notes*/
    @PostMapping("/save-notes-without-image")
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
    public ResponseEntity<Note> updateNote(@PathVariable(value="id") Integer noteId,@RequestParam(value = "userId")String userId,@RequestParam(value = "title")String title, @RequestParam(value = "description") String description, @RequestParam(value = "dateTime") String dateTime, @RequestParam(value = "favorite") Boolean favorite,@RequestParam(value = "image") MultipartFile file) throws IOException {

        Note nte = noteDAO.findOne(noteId);

        if(nte==null) {
            return ResponseEntity.notFound().build();
        }

        String filePath = FOLDER_PATH+file.getOriginalFilename();


        nte.setTitle(title);
        nte.setDescription(description);
        nte.setDateTime(dateTime);
        nte.setFile_path(file.getOriginalFilename());
        nte.setFavorite(favorite);

        Note updateNote = noteDAO.save(nte);
        file.transferTo(new File(filePath));
        return ResponseEntity.ok().body(updateNote);
    }

    /*update a note by note id*/
    @PutMapping("/update-note-without-image/{id}")
    public ResponseEntity<Note> updateNote(@PathVariable(value="id") Integer noteId,@RequestBody Note noteDetails){

        Note nte = noteDAO.findOne(noteId);

        if(nte==null) {
            return ResponseEntity.notFound().build();
        }

        nte.setTitle(noteDetails.getTitle());
        nte.setDescription(noteDetails.getDescription());
        nte.setDateTime(noteDetails.getDateTime());
      //  nte.setFile_path(noteDetails.getFile_path());
      //  nte.setFavorite(noteDetails.getFavorite());

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

    /*Update a note*/
    @PutMapping("/update-note-favorite/{id}/{isFavStatus}")
    public ResponseEntity<Note> updateIsFavorite(@PathVariable(value="id") Integer noteId,@PathVariable(value="isFavStatus") boolean isFavorite){

        Note nte = noteDAO.findOne(noteId);
        if(nte==null) {
            return ResponseEntity.notFound().build();
        }
        noteDAO.updateIsFavorite(nte,isFavorite);

        return ResponseEntity.ok().build();
    }

    /*get all  favorite notes*/
    @GetMapping("/get-all-favorites/{uId}")
    public List<Note> getAllFavoriteNotes(@PathVariable(value="uId") String uId){
        return noteDAO.getFavoriteNotes(uId);
    }

    /*get notes by userId*/
    @GetMapping("/get-notes-by-user-id/{uId}")
    public ResponseEntity<List<Note>> getNoteByUserId(@PathVariable(value="uId") String uId){
        List<Note> nte = noteDAO.getNotesByUserId(uId);

        if(nte==null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(nte);
    }



}
