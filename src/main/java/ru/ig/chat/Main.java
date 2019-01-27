package ru.ig.chat;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("CONSOLE CHAT");
        Scanner in = new Scanner(System.in);
        boolean menuIsRunning = true;
        while (menuIsRunning) {
            System.out.println("Select run mode:");
            System.out.println("1. Client mode");
            System.out.println("2. Server mode");
            System.out.println("0. Exit");
            int choice = in.nextInt();
            switch (choice) {
                case 1:
                    menuIsRunning = false;
                    new ChatClient();
                    break;
                case 2:
                    menuIsRunning = false;
                    new ChatServer();
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.println("Wrong input");
                    break;
            }
        }
    }
}