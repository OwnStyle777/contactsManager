package contacts;

import contacts.validation.ValidationOfPhoneNumber;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public interface RecordsManager {
    ValidationOfPhoneNumber validation = new ValidationOfPhoneNumber();

    default void addRecord(List<Record> recordList, Scanner scanner) {
        System.out.println("Enter the type (person, organization):");
        String type = scanner.nextLine();

        if (type.equals("person")) {
            System.out.println("Enter the name:");
            String name = scanner.nextLine();
            System.out.println("Enter the surname:");
            String surname = scanner.nextLine();
            System.out.println("Enter the birth date:");
            String birthDate = scanner.nextLine();
            if (birthDate.equals("")) {
                System.out.println("Bad birth date!");
            }
            System.out.println("Enter the gender (M, F):");
            String gender = scanner.nextLine();
            if (!gender.equals("M") && !gender.equals("F")) {
                System.out.println("Bad gender!");
            }
            System.out.println("Enter the number:");
            String number = scanner.nextLine();
            if (validation.isPhoneNumberValidated(number)) {
                Record record = new Person(name, surname, number, birthDate, gender, LocalDateTime.now(), LocalDateTime.now());
                recordList.add(record);
                System.out.println("The record added!");
            } else {
                Record record = new Person(name, surname, "[no number]", birthDate, gender, LocalDateTime.now(), LocalDateTime.now());
                recordList.add(record);
                System.out.println("Wrong number format!");
                System.out.println("The record added!");
            }
        } else if (type.equals("organization")) {
            System.out.println("Enter the organization name:");
            String name = scanner.nextLine();
            System.out.println("Enter the address:");
            String address = scanner.nextLine();
            System.out.println("Enter the number:");
            String number = scanner.nextLine();
            if (validation.isPhoneNumberValidated(number)) {
                Record record = new Organization(name, address, number, LocalDateTime.now(), LocalDateTime.now());
                recordList.add(record);
                System.out.println("The record added!");
            } else {
                Record record = new Organization(name, address, "[no number]", LocalDateTime.now(), LocalDateTime.now());
                recordList.add(record);
                System.out.println("Wrong number format!");
                System.out.println("The record added!");
            }
        } else {
            System.out.println("Incorrect type");
        }


    }

    ;

    default void removeRecord(List<Record> recordList) {
        Scanner scanner = new Scanner(System.in);
        if (recordList.isEmpty()) {
            System.out.println("No records to remove!");
        } else {
            getListOfRecords(recordList);
            System.out.println("Select a record:");
            int recordID = scanner.nextInt();
            Record record = recordList.get(recordID - 1);
            if (recordList.contains(record)) {
                recordList.remove(record);
                System.out.println("The record removed!");
            } else {
                System.out.println("No record with this ID");
            }

        }
    }

    ;


    default void edit(Record record, Scanner scanner) {
        String editableFields = record.getEditableFields();
        System.out.println(editableFields);
        String field = scanner.nextLine();
        if (editableFields.contains(field)) {
            System.out.println("Enter " + field + ":");
            String fieldValue = scanner.nextLine();
            record.updateField(field, fieldValue);
            record.setEdited(LocalDateTime.now());
            System.out.println("Saved");
        } else {
            System.out.println("Incorrect field");
        }
    }

    default void countRecords(List<Record> recordList) {
        System.out.println("The Phone Book has " + (recordList.size()) + " records.");

    }

    ;

    default void getListOfRecords(List<Record> recordList) {
        int recordId = 1;
        for (Record record : recordList) {
            System.out.println(recordId + ". " + record.toString());
            recordId++;
        }
    }

    default void listFunction(List<Record> recordList, Scanner scanner) {

        getListOfRecords(recordList);
        if (recordList.size() > 0) {
            System.out.println("[list] Enter action ([number], back):");
            String number = scanner.nextLine();
            if (number.equals("back")) {
            } else {
                try {
                    Record record = recordList.get(Integer.parseInt(number) - 1);
                    record.getInfoAboutRecord();
                    System.out.println();
                    System.out.println("[record] Enter action (edit, delete, menu):");
                    String command = scanner.nextLine();
                    if (command.equals("edit")) {
                        edit(record, scanner);
                    } else if (command.equals("delete")) {
                        removeRecord(recordList);

                    } else if (command.equals("menu")) {

                    } else {
                        System.out.println("Wrong command");
                    }
                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    System.out.println("Wrong number");
                }
            }
        } else {
            System.out.println("No records in list");
        }


    }

    default void fillEmptyFields(List<Record> recordList) {
        for (Record record : recordList) {
            if (record.isPerson) {
                Person person = (Person) record;
                String gender = person.getGender();
                String name = person.getName();
                String surname = person.getSurname();
                String birth = person.getBirthDate();

                if (gender.equals("")) {
                    person.setGender("[no data]");
                }
                if (name.equals("")) {
                    person.setName("[no data]");
                }
                if (surname.equals("")) {
                    person.setSurname("[no data]");
                }
                if (birth.equals("")) {
                    person.setBirthDate("[no data]");
                }
            } else {
                Organization organization = (Organization) record;
                String address = organization.getAddress();
                String name = organization.getName();

                if (address.equals("")) {
                    organization.setAddress("[no data]");
                }
                if (name.equals("")) {
                    organization.setName("[no data]");
                }
            }
        }
    }

    default List<Record> foundRecords(List<Record> recordList) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter search query:");
        String query = scanner.nextLine();
        List<Record> foundRecords = new ArrayList<>();
        for (Record record : recordList) {
            String text = record.toString();
            if (text.toLowerCase().contains(query.toLowerCase())) {
                foundRecords.add(record);
            }
            if (record.getPhoneNumber().contains(query)) {
                foundRecords.add(record);
            }
        }
        int numberOfFoundRecord = 1;
        System.out.println("Found " + foundRecords.size() + " results:");
        for (Record record : foundRecords) {

            System.out.println(numberOfFoundRecord + ". " + record);
            numberOfFoundRecord++;
        }
        return foundRecords;
    }

    default void search(List<Record> recordList, Scanner scanner) {
        boolean back = false;
        List<Record> foundRecords = foundRecords(recordList);

        if (foundRecords.size() > 0) {
            while (!back) {
                System.out.println("[search] Enter action ([number], back, again):");
                String number = scanner.nextLine();

                if (number.equals("back")) {
                    back = true;
                } else if (number.equals("again")) {
                    foundRecords(recordList);
                } else {
                    try {
                        Record record = foundRecords.get(Integer.parseInt(number) - 1);
                        record.getInfoAboutRecord();
                        System.out.println("[record] Enter action (edit, delete, menu):");
                        String command = scanner.nextLine();
                        if (command.equals("edit")) {
                            edit(record, scanner);
                        } else if (command.equals("delete")) {
                            removeRecord(recordList);

                        } else if (command.equals("menu")) {
                            back = true;
                        } else {
                            System.out.println("Wrong command");
                        }
                    } catch (NumberFormatException | IndexOutOfBoundsException e) {
                        System.out.println("No record with this number");
                    }
                }
            }
        }
    }
}
