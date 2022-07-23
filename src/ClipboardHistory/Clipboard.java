package ClipboardHistory;

import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

class Clipboard {

    static Stack<String> stored = new Stack<String>(20);

    public static String readClipboard() throws HeadlessException, UnsupportedFlavorException, IOException {
        return (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
    }

    public static void run(int time) {
        String current = "";
        while (time >= 0) {
            String temp = "";
            try {
                temp = readClipboard();
            } catch (HeadlessException | IOException e) {
                e.printStackTrace();
            } catch (UnsupportedFlavorException e) {
                run(time);
            }


            if (!temp.equals(current)) {
                stored.push(temp);
                current = temp;
                System.out.println(current);
            }

            try {
                Thread.sleep(1000);
                --time;
            } catch (InterruptedException e) {
                System.out.println("Program Halted");
            }
        }
    }

    public static void stored() {
        Stack<String> temp = stored.copy();
        for (int i = 0; i < (stored.top() + 1); ++i) {
            System.out.println((i+1) + ": " + temp.pop());
        }
    }

    public static boolean isEmpty() {
        return stored.isEmpty();
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
        Stack<T> temp = new Stack<T>(size);
        for (int i = 0; i < (top + 1); ++i) {
            temp.push(stack[i]);
        }
        return temp;
    }
}