package ru.ig.chat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import static ru.ig.chat.Constants.EXIT_COMMAND;

abstract class Chat {
    static Socket socket;
    static BufferedReader in;
    static BufferedWriter out;
    IncomeListener listener;
    static boolean isRunning;


    private static String readLineFromConsole() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    static String readIPFromConsole() {
        System.out.println("Enter hostname or ip");
        return readLineFromConsole();
    }

    static void sendingMessage() throws IOException {
        while (isRunning) {
            String message = readLineFromConsole();
            if (message.equals(EXIT_COMMAND)) {
                isRunning = false;
                out.write(EXIT_COMMAND);
            } else {
                out.write(message + "\n");
            }
            out.flush();
        }
    }
}