package MD5;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class MD5Main {

    static Stack<String> stack = new Stack<String>(20);

    public static void main(String[] args) throws NoSuchAlgorithmException, IOException, InterruptedException {

        Scanner scanner = new Scanner(System.in);

        String fileName = "Test.txt";
        File file = new File(fileName);
        String checksum = "";

        while (true) {
            System.out.print("Command: ");
            String command = scanner.nextLine();

            if (command.startsWith("check")) {
                String[] parts = command.split(" ");

                if (parts.length != 2) {
                    System.out.println("Invalid command");
                    continue;
                }

                int time = Integer.parseInt(parts[1]);
                
                for (int i = 0; i < time; ++i) {
                    String temp = Checksum.read(file);

                    if (!temp.equals(checksum)) {
                        System.out.println("Checksum: " + temp);
                        stack.push(temp);
                        checksum = temp;
                    }

                    Thread.sleep(1000 * time);
                }
            }

            else if (command.equals("history")) {
                history();
            }

            else if (command.equals("help")) {
                help();
            }

            else if (command.equals("exit")) {
                break;
            }

            else {
                System.out.println("Invalid command");
            }
        }
    }

    private static void help() {
        System.out.println("Commands:");
        System.out.println("check <time> - checks MD5 hash of file every <time> seconds");
        System.out.println("history - prints history of file checksum changes");
        System.out.println("help - prints this help");
        System.out.println("exit - exits program");
    }

    public static void history() {
        if (stack.isEmpty()) {
            System.out.println("No history of files changes");
        }

        Stack<String> temp = stack.copy();
        System.out.println("History: ");
        for (int i = 0; i < (stack.top() + 1); i++) {
            System.out.println(temp.pop());
        }
    }
}

class Stack<T> {
    private T[] stack;
    private int top;
    private int size;

    public Stack(int size) {
        this.size = size;
        stack = (T[]) new Object[size];
        top = -1;
    }

    public Stack() {
        this(10);
    }

    public void push(T item) {
        if (top == size - 1) {
            T[] newStack = (T[]) new Object[size * 2];
            for (int i = 0; i < size; ++i) {
                newStack[i] = stack[i];
            }
            stack = newStack;
            size *= 2;
        } else {
            ++top;
            stack[top] = item;
        }
    }

    public T pop() {
        if (top == -1) {
            System.out.println("Nothing is copied");
            return null;
        } else {
            T item = stack[top];
            --top;
            return item;
        }
    }

    public T peek() {
        if (top == -1) {
            System.out.println("Nothing is copied");
            return null;
        } else {
            return stack[top];
        }
    }

    public T peek(int index) {

        if (index < 0 || index > top) {
            System.out.println("Invalid index");
            return null;
        }

        if (top == -1) {
            System.out.println("Nothing is copied");
            return null;
        } else {
            return stack[index];
        }
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == size - 1;
    }

    public int size() {
        return size;
    }

    public int top() {
        return top;
    }

    public Stack<T> copy() {
        Stack<T> copy = new Stack<>(size);
        for (int i = 0; i <= top; ++i) {
            copy.push(stack[i]);
        }
        return copy;
    }
}