package ClipboardHistory;

import java.util.Scanner;

class ClipUI {

    public static void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Command: ");
            String input = scanner.nextLine();

            if (input.equals("exit")) {
                break;
            }

            else if (input.equals("history")) {
                print();
            }

            else if (input.equals("help")) {
                help();
            }

            else if (input.startsWith("copy")) {
                String[] splits = input.split(" ");
                
                if (splits.length != 2) {
                    System.out.println("Invalid Command");
                    continue;
                }

                int time = Integer.parseInt(splits[1]);
                copy(time);
            
            } else {
                System.out.println("Unknown command");
            }

        }
    }

    private static void copy(int time) {
        System.out.println("                               Copy to Clipboard History                        ");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("                                                                                ");
        Clipboard.run(time);
    }

    private static void help() {
        System.out.println("---------------------------Available Commands---------------------------");
        System.out.println("history                                  - prints previously copied text until copy command is used again");
        System.out.println("exit                                     - halts the program");
        System.out.println("help                                     - shows this help");
        System.out.println("copy <time>                              - copies the text from the clipboard for certain time");
    }

    private static void print() {
        if (Clipboard.isEmpty()) {
            System.out.println("No history");
            return;
        }

        System.out.println("                               Clipboard History                                ");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("                                                                                ");
        Clipboard.stored();
    }
}