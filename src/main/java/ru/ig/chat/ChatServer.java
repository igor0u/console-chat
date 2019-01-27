package ru.ig.chat;

import java.io.*;
import java.net.ServerSocket;

import static ru.ig.chat.Constants.EXIT_COMMAND;
import static ru.ig.chat.Constants.PORT_NUMBER;

class ChatServer extends Chat {
    private static ServerSocket server;

    ChatServer() {
        System.out.println("CONSOLE CHAT SERVER");
        System.out.println("Waiting for client connection");
        isRunning = true;
        try {
            try {
                server = new ServerSocket(PORT_NUMBER);
                socket = server.accept();
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                listener = new IncomeListener(in);
                listener.start();
                System.out.println("Client connected");
                System.out.println("Command for exit: " + EXIT_COMMAND);
                sendingMessage();
            } finally {
                socket.close();
                in.close();
                out.close();
                server.close();
                isRunning = false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            isRunning = false;
        }
    }
}