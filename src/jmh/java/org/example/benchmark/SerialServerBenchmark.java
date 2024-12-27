package org.example.benchmark;

import org.example.helper.DAO;
import org.example.serialClient.Client;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.results.RunResult;

import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 2)
@Measurement(iterations = 3)
@Fork(1)
/**
 * Benchmark-Klass für die Messung der Leistung des Servers.
 */
public class SerialServerBenchmark {

    private DAO dao;

    @Setup
    public void setup() {
        dao = DAO.getDao();
    }

    @Benchmark
    public void benchmark1Client() {
        runClientsAndWait(1);
    }

    @Benchmark
    public void benchmark2Clients() {
        runClientsAndWait(2);
    }

    @Benchmark
    public void benchmark3Clients() {
        runClientsAndWait(3);
    }

    @Benchmark
    public void benchmark4Clients() {
        runClientsAndWait(4);
    }

    @Benchmark
    public void benchmark5Clients() {
        runClientsAndWait(5);
    }

    private void runClientsAndWait(int numClients) {
        Thread[] threads = new Thread[numClients];

        // Start threads
        for (int j = 0; j < numClients; j++) {
            threads[j] = new Thread(new Client(dao));
            threads[j].start();
        }

        // Wait for completion
        for (int j = 0; j < numClients; j++) {
            try {
                threads[j].join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) throws RunnerException {
        // Generate timestamp for filename
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
        String resultsFile = String.format("benchmark_results_%s", timestamp);

        Options opt = new OptionsBuilder()
                .include(SerialServerBenchmark.class.getSimpleName())
                .resultFormat(ResultFormatType.JSON) // Save as JSON
                .result(resultsFile + ".json") // JSON results
                .build();

        Collection<RunResult> results = new Runner(opt).run();
        // Shutdown server after benchmarks
        System.out.println("Benchmarks complete, shutting down server...");
        System.out.println("Results saved to: " + resultsFile + ".json and " + resultsFile + ".csv");
        Thread shutdownThread = new Thread(new Client(DAO.getDao(), true));
        shutdownThread.start();
    }
}