package lk.acpt.note_collector_app.model;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,generator = "id")
    @Column(name = "noteId")
    private Integer noteId;
    private String userId;
    private String title;
    private String description;
    private String dateTime;
    private String file_path;
    private Boolean isFavorite;

    public Note() {

    }

    public Note(String userId, String title, String description, String dateTime,Boolean isFavorite ,String file_path) {
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.dateTime = dateTime;
        this.isFavorite = isFavorite;
        this.file_path = file_path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public Integer getNoteId() {
        return noteId;
    }

    public void setNoteId(Integer noteId) {
        this.noteId = noteId;
    }


    public Boolean getFavorite() {
        return isFavorite;
    }

    public void setFavorite(Boolean favorite) {
        isFavorite = favorite;
    }

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
