package ru.ig.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.SocketException;

public class IncomeListener extends Thread {
    private BufferedReader in;

    IncomeListener(BufferedReader in) {
        this.in = in;
    }

    @Override
    public void run() {
        try {
            while (Chat.isRunning) {
                if (in.readLine().equals(Constants.EXIT_COMMAND)) {
                    Chat.isRunning = false;
                    System.out.println("Interlocutor has left the chat");
                } else {
                    System.out.print(in.readLine() + "\n");
                }
            }
        } catch (SocketException e) {
            System.out.println("Connection was terminated by server");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error while retrieving message");
        }
    }
}