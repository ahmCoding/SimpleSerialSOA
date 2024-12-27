package org.example.serialServer;

import org.example.dataStructures.command.*;
import org.example.helper.Config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] arg) {
        boolean stopServer = false;
        System.out.println("Initialization completed.");
        try (ServerSocket serverSocket = new ServerSocket(Config.SERIAL_PORT, 50, InetAddress.getByName("127.0.0.1"))) {
            do {
                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));) {
                    String line = in.readLine();
                    Command command;
                    String[] commandData = line.split(";");
                    System.err.println("Command: " + commandData[0]);
                    switch (commandData[0]) {
                        case "q":
                            System.err.println("Query");
                            command = new QueryCommand(commandData);
                            break;
                        case "r":
                            System.err.println("Report");
                            command = new ReportCommand(commandData);
                            break;
                        case "z":
                            System.err.println("Stop");
                            command = new ShutdownCommand(commandData);
                            stopServer = true;
                            break;
                        default:
                            System.err.println("Error");
                            command = new ErrorCommand(commandData);
                    }
                    String response = command.execute();
                    System.err.println(response);
                    out.println(response);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } while (!stopServer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
