package ru.ig.chat;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;

import static ru.ig.chat.Constants.EXIT_COMMAND;
import static ru.ig.chat.Constants.PORT_NUMBER;

class ChatClient extends Chat {
    ChatClient() {
        System.out.println("CONSOLE CHAT CLIENT");
        isRunning = true;
        try {
            try {
                socket = new Socket(readIPFromConsole(), PORT_NUMBER);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                listener = new IncomeListener(in);
                listener.start();
                System.out.println("Connected to server");
                System.out.println("Command for exit: " + EXIT_COMMAND);
                sendingMessage();
            } finally {
                socket.close();
                in.close();
                out.close();
                isRunning = false;
            }
        } catch (SocketException e) {
            System.out.println("Connection was terminated by server");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}