package contacts;

import java.io.Serializable;
import java.time.LocalDateTime;

public abstract class Record implements Serializable {
    private String name;
    private String phoneNumber;
    private LocalDateTime created;
    private LocalDateTime edited;
    final boolean isPerson;


    public Record(String name, String phoneNumber, boolean isPerson, LocalDateTime created, LocalDateTime edited) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.isPerson = isPerson;
        this.created = created;
        this.edited = edited;
    }

    public abstract void updateField(String field, String value);

    public abstract String returnValue(String field);

    public abstract void getInfoAboutRecord();

    public abstract String getEditableFields();

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isPerson() {
        return isPerson;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getEdited() {
        return edited;
    }

    public void setEdited(LocalDateTime edited) {
        this.edited = edited;
    }
}
