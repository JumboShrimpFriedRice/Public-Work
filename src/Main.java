import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        HashMap<String, Integer> currentMap = db.createMap();
        while (stdin.hasNextLine()) {
            String rawCommand = stdin.nextLine();
            String [] commands = rawCommand.split("\\s+");
            switch(commands[0]) {
                case "END":
                    System.exit(0);
                    break;
                case "SET":
                    db.set(currentMap, commands[1],Integer.parseInt(commands[2]));
                    break;
                case "GET":
                    db.get(currentMap, commands[1]);
                    break;
                case "UNSET":
                    db.unset(currentMap, commands[1]);
                    break;
                case "NUMEQUALTO":
                    db.numEqualTo(currentMap, Integer.parseInt(commands[1]));
                    break;
                case "BEGIN":
                    //db.begin();
                    if (db.checkStackSize() == 0) {
                        db.begin(currentMap);
                        break;
                    }
                    currentMap = db.beginWithAdd(currentMap);
                    break;
                case "ROLLBACK":
                    if (db.rollBack() == null) {
                        System.out.println("NO TRANSACTION");
                        break;
                    }
                    currentMap = db.rollBack();
                    break;
                case "COMMIT":
                    db.commit();
                    break;
                default:
                    System.out.println("SET name value – Set the variable name to the value value. Neither variable names nor values will contain spaces.\n" +
                            "\n" +
                            "GET name – Print out the value of the variable name, or NULL if that variable is not set.\n" +
                            "\n" +
                            "UNSET name – Unset the variable name, making it just like that variable was never set.\n" +
                            "\n" +
                            "NUMEQUALTO value – Print out the number of variables that are currently set to value. If no variables equal that value, print 0.\n" +
                            "\n" +
                            "END – Exit the program. Your program will always receive this as its last command.\n" +
                            "\n" +
                            "BEGIN – Open a new transaction block. Transaction blocks can be nested; a BEGIN can be issued inside of an existing block.\n" +
                            "\n" +
                            "ROLLBACK – Undo all of the commands issued in the most recent transaction block, and close the block. Print nothing if successful, or print NO \n" +
                            "\n" +
                            "TRANSACTION if no transaction is in progress.\n" +
                            "\n" +
                            "COMMIT – Close all open transaction blocks, permanently applying the changes made in them.");
            }

        }
    }
}
