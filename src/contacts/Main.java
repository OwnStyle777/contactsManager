package contacts;

import contacts.serialization.SaveAndLoadFunctions;

import java.util.*;

public class Main {
    private static final RecordsManager recordsManager = new RecordsManager() {
    };
    private static final SaveAndLoadFunctions saveOrLoad = new SaveAndLoadFunctions() {
    };
    private static List<Record> recordList = new ArrayList<>();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        boolean exit = false;
        while (!exit) {
            System.out.println("[menu] Enter action (add, list, search, count, save, load, exit):");
            String command = scanner.nextLine();
            switch (command) {
                case "add":
                    recordsManager.addRecord(recordList, scanner);

                    recordsManager.fillEmptyFields(recordList);
                    System.out.println();
                    break;

                case "count":
                    recordsManager.countRecords(recordList);
                    System.out.println();
                    break;
                case "list":
                    recordsManager.listFunction(recordList, scanner);
                    System.out.println();
                    break;
                case "search":
                    recordsManager.search(recordList);
                    System.out.println();
                    break;
                case "exit":
                    exit = true;
                    break;
                case "save":
                    saveOrLoad.save(scanner, recordList);
                    break;
                case "load":
                    recordList = saveOrLoad.load(scanner, recordList);
                    break;
                default:
                    System.out.println("Wrong command");
                    break;
            }
        }
    }
}
