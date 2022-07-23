package WebStatus;

import java.io.IOException;
import java.util.Scanner;

class UI {

    static Stack<String> stack = new Stack<>(20);
    
    public static void run() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Command: ");
            String command = scanner.nextLine();

            if (command.equals("exit")) {
                break;
            }

            else if (command.startsWith("check")) {
                String[] parts = command.split(" ");

                if (parts.length == 2) {
                    try {
                        int status = WebStatus.status(parts[1]);
                        System.out.println(parts[1] + ": " + status);
                        stack.push(parts[1] + ": " + status + " - " + WebStatus.interpret(status));
                    } catch (IOException e) {
                        System.out.println("Invalid URL");
                    }
                } else {
                    System.out.println("Invalid command");
                }
            }

            else if (command.startsWith("cat")) {
                String[] parts = command.split(" ");

                if (parts.length == 2) {
                    try {
                        int status = WebStatus.status(parts[1]);
                        System.out.println(parts[1] + ": " + status);
                        stack.push(parts[1] + ": " + status + " - " + WebStatus.interpret(status));
                        WebStatus.getCatPic(status);
                        System.exit(0);
                    } catch (IOException e) {
                        System.out.println("Invalid URL");
                    }
                } else {
                    System.out.println("Invalid command");
                }
            }

            else if (command.equals("help")) {
                help();
            }

            else if (command.equals("history")) {
                print();
            } 
            
            else {
                System.out.println("Invalid command");
            }
        }
    }

    private static void help() {
        System.out.println("---------------------------Available Commands---------------------------");
        System.out.println("history                                  - prints previous status checks");
        System.out.println("exit                                     - halts the program");
        System.out.println("help                                     - shows this help");
        System.out.println("check <URL>                              - checks the current status of a website");
        System.out.println("cat <URL>                                - checks the current status of a website, displays a cat picture and halts the program");
    }

    private static void print() {
        if (stack.isEmpty()) {
            System.out.println("No previous status checks");
            return;
        }

        System.out.println("                                 Status History                                 ");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("                                                                                ");
        printHistory();
    }

    private static void printHistory() {
        Stack<String> temp = stack.copy();
        for (int i = 0; i < stack.top(); ++i) {
            System.out.println((i+1) + ": " + temp.pop());
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