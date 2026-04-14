package runner;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Server {

    public static void start(AlgorithmRunner runner) throws Exception {
        int port = Integer.parseInt(System.getenv().getOrDefault("PORT", "8080"));
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

        // Endpoint: /run
        server.createContext("/run", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                if (!exchange.getRequestMethod().equalsIgnoreCase("POST")) {
                    exchange.sendResponseHeaders(405, -1);
                    return;
                }

                String code = new String(exchange.getRequestBody().readAllBytes());
                try {
                    runner.setup(code);
                    runner.runHeadless(); // You will implement this
                    String response = "Algorithm executed successfully.";
                    exchange.sendResponseHeaders(200, response.length());
                    exchange.getResponseBody().write(response.getBytes());
                } catch (Exception e) {
                    String response = "Error: " + e.getMessage();
                    exchange.sendResponseHeaders(500, response.length());
                    exchange.getResponseBody().write(response.getBytes());
                } finally {
                    exchange.close();
                }
            }
        });

        server.start();
        System.out.println("Server running on port " + port);
    }
}
