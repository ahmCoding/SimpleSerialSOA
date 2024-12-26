package org.example.serialClient;

import org.example.dataStructures.wdi.WDI;
import org.example.helper.Config;
import org.example.helper.DAO;

import java.io.*;
import java.net.Socket;
import java.util.List;
import java.util.Random;

/**
 * Klasse zur Implementierung des seriellen Clients.
 * Jedes Client-Objekt führt 10 Iterationen von jeweils 9 Query-Abfragen und einer Report-Abfrage durch.
 * Die Klasse wird dann zur Erzeugung von mehreren Client-Objekten verwendet, die in Threads ausgeführt werden.
 */
public class Client implements Runnable {

    private static final int QUERY_ITERATIONS = 10;
    private static final int QUERIES_PER_ITERATION = 9;

    private final DAO dao;
    private final Random random;

    public Client() {
        dao = DAO.getDao();
        random = new Random();
    }

    @Override
    public void run() {
        List<WDI> data = dao.getDataset();

        for (int i = 0; i < QUERY_ITERATIONS; i++) {
            executeQueryBatch(data);
            executeReportQuery(data);
        }
    }

    private WDI getRandomWDI(List<WDI> data) {
        return data.get(random.nextInt(data.size()));
    }

    private String buildQueryCommand(WDI wdi) {
        return String.format("q;%s;%s", wdi.getCountryCode(), wdi.getIndicatorCode());
    }

    private String buildReportCommand(WDI wdi) {
        return String.format("r;%s", wdi.getIndicatorCode());
    }

    /**
     * Führt einen Befehl auf dem Server aus.
     *
     * @param command Befehl
     */
    private void executeCommand(String command) {
        try (Socket socket = new Socket("localhost", Config.SERIAL_PORT);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            out.println(command);
            String response = in.readLine();
            System.err.println("Server response: " + response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Führt eine Batch von Query-Befehlen auf dem Server aus.
     *
     * @param data Liste von WDI-Objekten
     */
    private void executeQueryBatch(List<WDI> data) {
        for (int j = 0; j < QUERIES_PER_ITERATION; j++) {
            WDI randomWdi = getRandomWDI(data);
            String command = buildQueryCommand(randomWdi);
            executeCommand(command);
        }
    }

    /**
     * Führt einen Report-Befehl auf dem Server aus.
     *
     * @param data Liste von WDI-Objekten
     */
    private void executeReportQuery(List<WDI> data) {
        WDI randomWdi = getRandomWDI(data);
        String command = buildReportCommand(randomWdi);
        executeCommand(command);
    }
}
