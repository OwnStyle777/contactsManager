package contacts;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Organization extends Record implements Serializable {
    private String address;


    public Organization(String name, String address, String phoneNumber, LocalDateTime created, LocalDateTime edited) {
        super(name, phoneNumber, false, created, edited);
        this.address = address;
    }

    @Override
    public String getEditableFields() {
        return "Select a field (name, address, number):";
    }

    @Override
    public void getInfoAboutRecord() {
        System.out.println("Organization name: " + getName());
        System.out.println("Address: " + getAddress());
        System.out.println("Number: " + getPhoneNumber());
        System.out.println("Time created: " + getCreated().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        System.out.println("Time last edit: " + getEdited().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }

    @Override
    public void updateField(String field, String value) {
        if (field.equals("address")) {
            setAddress(value);
        } else if (field.equals("name")) {
            setName(value);
        } else if (field.equals("number")) {
            setPhoneNumber(value);
        } else {
            System.out.println("Wrong field" + field);
        }
    }

    @Override
    public String returnValue(String field) {
        if (field.equals("name")) {
            return getName();
        } else if (field.equals("number")) {
            return getPhoneNumber();
        } else if (field.equals("address")) {
            return getAddress();
        }
        return "wrong field" + field;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String toString() {
        return super.getName();
    }

}
