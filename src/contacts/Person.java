package contacts;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Person extends Record implements Serializable {

    private String surname;
    private String birthDate;
    private String gender;


    public Person(String name, String surname, String phoneNumber, String birthDate, String gender, LocalDateTime created, LocalDateTime edited) {
        super(name, phoneNumber, true, created, edited);
        this.surname = surname;
        this.birthDate = birthDate;
        this.gender = gender;
    }

    @Override
    public String getEditableFields() {
        return "Select a field (name, surname, birth, gender, number):";

    }

    @Override
    public void getInfoAboutRecord() {
        System.out.println("Name: " + getName());
        System.out.println("Surname: " + getSurname());
        System.out.println("Birth date: " + getBirthDate());
        System.out.println("Gender: " + getGender());
        System.out.println("Number: " + getPhoneNumber());
        System.out.println("Time created: " + getCreated().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        System.out.println("Time last edit: " + getEdited().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }

    @Override
    public void updateField(String field, String value) {
        if (field.equals("name")) {
            setName(value);
        } else if (field.equals("surname")) {
            setSurname(value);
        } else if (field.equals("number")) {
            setPhoneNumber(value);
        } else if (field.equals("birth")) {
            setBirthDate(value);
        } else if (field.equals("gender")) {
            setGender(value);
        } else {
            System.out.println("Wrong field " + field);
        }
    }

    @Override
    public String returnValue(String field) {
        if (field.equals("name")) {
            return getName();
        } else if (field.equals("surname")) {
            return getSurname();
        } else if (field.equals("number")) {
            return getPhoneNumber();
        } else if (field.equals("birth")) {
            return getBirthDate();
        } else if (field.equals("gender")) {
            return getGender();
        }
        return "Wrong field" + field;
    }

    @Override
    public String toString() {
        return super.getName() + " " + surname;
    }


    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }


    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

}
