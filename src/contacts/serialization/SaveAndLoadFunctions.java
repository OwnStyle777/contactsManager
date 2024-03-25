package contacts.serialization;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public interface SaveAndLoadFunctions {
    default void save(Scanner scanner, List<contacts.Record> recordList) {
        System.out.println("Enter file name");
        String fileName = scanner.nextLine();
        String pathname = "src/contacts/savedContacts/" + fileName + ".ser";
        File file = new File(pathname);

        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(file));
            os.writeObject(recordList);
            System.out.println("List of records was successfully saved.");
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    default List<contacts.Record> load(Scanner scanner, List<contacts.Record> recordList) {

        System.out.println("Enter file name:");
        String fileName = scanner.nextLine();
        String pathname = "src/contacts/savedContacts/" + fileName + ".ser";
        try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(pathname))) {
            recordList = (List<contacts.Record>) is.readObject();
            System.out.println("List of records was successfully loaded .");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return recordList;

    }
}
