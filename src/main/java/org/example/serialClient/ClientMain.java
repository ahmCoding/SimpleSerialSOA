package org.example.serialClient;

import org.example.helper.DAO;

public class ClientMain {
    public static void main(String[] args) {
        Client client = new Client();
        Thread t = new Thread(client);
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

}
