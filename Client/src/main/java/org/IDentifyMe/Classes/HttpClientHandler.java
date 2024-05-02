package org.IDentifyMe.Classes;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletionException;
import java.util.function.Function;
import javafx.scene.control.Alert;

import org.IDentifyMe.MainApp;

public class HttpClientHandler {
    private HttpClient client;
    private final String BASE_URL = "http://localhost:8081";
    private final String PORT = "8081";
    private Function<Throwable, ? extends String> handleException;

    public HttpClientHandler() {
        this.client = HttpClient.newHttpClient();
        this.handleException = (error) -> {
            if (error instanceof CompletionException && error.getCause() instanceof InterruptedException) {
                MainApp.router.CreatePopup("Error", "An error occurred while sending the request",
                        Alert.AlertType.ERROR, true, error.getCause().getMessage());

            } else if (error instanceof CompletionException && error.getCause() instanceof IOException) {
                MainApp.router.CreatePopup("Error", "An error occurred while sending the request",
                        Alert.AlertType.ERROR, true, error.getCause().getMessage());

            } else {
                MainApp.router.CreatePopup("Error", "An error occurred while sending the request",
                        Alert.AlertType.ERROR, true, error.getMessage());
            }
            return null;
        };
    }

    public void sendGetRequest(String url, Function<HttpResponse<String>, String> validator) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(BASE_URL + url))
                    .GET()
                    .build();

            client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(validator)
                    .exceptionally(this.handleException);
        } catch (URISyntaxException e) {
            MainApp.router.CreatePopup("Error", "An error occurred while sending the request", Alert.AlertType.ERROR,
                    true, e.getMessage());
        }
    }

    public void sendPostRequest(String url, Function<HttpResponse<String>, String> validator, String data) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(BASE_URL+url))
                    .POST(HttpRequest.BodyPublishers.ofString(data))
                    .build();

            client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(validator)
                    .exceptionally(this.handleException);
        } catch (URISyntaxException e) {
            MainApp.router.CreatePopup("Error", "An error occurred while sending the request", Alert.AlertType.ERROR,
                    true, e.getMessage());
        }
    }
}