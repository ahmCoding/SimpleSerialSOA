# Service-Oriented Architecture (SOA) Server-Client Implementation

This project demonstrates the realization of a Service-Oriented Architecture (SOA) in the form of a simple server-client
system. The architecture enables clients to send requests to the server, which then processes these requests using
commands executed on a dataset derived from the "World Development Indicators." Results are then returned to the
respective clients.

## Features

- **Client-Server Communication:**
    - Clients send requests to the server, which processes commands and returns the results.
- **In-Memory Dataset:**
    - The "World Development Indicators" dataset is stored in memory for fast access. Optionally, data can also be
      persisted in a database.
- **Performance Benchmarking:**
    - Benchmarks using Java Microbenchmark Harness (JMH) to measure server performance under varying conditions, such as
      the number of clients and requests.

## Key Components

### Server

- Processes client requests.
- Executes commands on the "World Development Indicators" dataset.
- Returns results to the clients.

### Client

- Sends requests to the server.
- Receives results from the server.

### Benchmarking

- Uses **JMH** to evaluate the server's performance.
- Measures response times and scalability with varying numbers of clients and requests.

## Technologies Used

- **Java**: Core programming language for server and client implementation.
- **Gradle**: Build tool for project management and execution.
- **JMH**: For benchmarking server performance.

## How to Run

### Prerequisites

- Java Development Kit (JDK) 11 or higher.
- Gradle (comes with the project via the `gradlew` wrapper).
- (Optional) Database setup if dataset persistence is required.

### Steps

1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd <repository-folder>
   ```

2. Start the server:
   ```bash
   ./gradlew runServer
   ```

3. Run a client instance and Benchmarking:
   ```bash
    ./gradlew jmh    
   ```

## Benchmarking Details

The benchmarking module evaluates the server's ability to handle:

- Increasing numbers of concurrent client connections.
- Varying request loads per client.

This helps identify performance bottlenecks and scalability limits.

### Benchmarking Results

- As Json file is generated in ./build/reports/jmh/results.json .

## Contact

For any questions or feedback, please open an issue in the repository.
