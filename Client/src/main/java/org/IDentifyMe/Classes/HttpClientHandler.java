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
    private HttpClient client = HttpClient.newBuilder().build();
    private final String BASE_URL = "http://localhost:8081";
    private static String cookie = "";


    private Function<Throwable, String> handleException = (error) -> {
        if (error instanceof CompletionException && error.getCause() instanceof InterruptedException) {
            MainApp.router.CreatePopup("Error", "An error occurred while sending the request",
                    Alert.AlertType.ERROR, true, error.getCause().getMessage());
        } else if (error instanceof CompletionException && error.getCause() instanceof IOException) {
            MainApp.router.CreatePopup("Error", "An error occurred while sending the request",
                    Alert.AlertType.ERROR, true, error.getCause().getMessage());
        } else {
            MainApp.router.CreatePopup("Error", "An error occurred while sending the request",
                    Alert.AlertType.ERROR, true, error.getMessage());
        };
        return null;
    };

    public void sendGetRequest(String url, Function<HttpResponse<String>, String> validator) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(BASE_URL + url))
                    .GET()
                    .header("Accept", "*/*")
                    .header("User-Agent", "Thunder Client (https://www.thunderclient.com)")
                    .header("Content-Type", "application/json")
                    .header("Cookie", cookie)
                    .build();

            client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(this::getCookie)
                    .thenApply(validator)
                    .exceptionally(this.handleException);
        } catch (URISyntaxException e) {
            MainApp.router.CreatePopup("Error", "An error occurred while sending the request", Alert.AlertType.ERROR,
                    true, e.getMessage());
        }
    }

    private HttpResponse<String> getCookie(HttpResponse<String> response) {
        if (response.headers().firstValue("Set-Cookie").isPresent()) {
            cookie = response.headers().firstValue("Set-Cookie").get();
            System.out.println("Cookie: " + cookie);
        }
        return response;
    }

    public void sendPostRequest(String url, Function<HttpResponse<String>, String> validator, String data) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(BASE_URL + url))
                    .POST(HttpRequest.BodyPublishers.ofString(data))
                    .header("Accept", "*/*")
                    .header("User-Agent", "Thunder Client (https://www.thunderclient.com)")
                    .header("Content-Type", "application/json")
                    .header("Cookie", cookie)
                    .build();

            client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(this::getCookie)
                    .thenApply(validator)
                    .exceptionally(this.handleException);
        } catch (URISyntaxException e) {
            MainApp.router.CreatePopup("Error", "An error occurred while sending the request", Alert.AlertType.ERROR,
                    true, e.getMessage());
        }
    }

    public void sendPostRequest(String url, Function<HttpResponse<String>, String> validator, byte[] data) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(BASE_URL + url))
                    .POST(HttpRequest.BodyPublishers.ofByteArray(data))
                    .header("Accept", "*/*")
                    .header("User-Agent", "Thunder Client (https://www.thunderclient.com)")
                    .header("Content-Type", "application/json")
                    .header("Cookie", cookie)
                    .build();

            client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(this::getCookie)
                    .thenApply(validator)
                    .exceptionally(this.handleException);
        } catch (URISyntaxException e) {
            MainApp.router.CreatePopup("Error", "An error occurred while sending the request", Alert.AlertType.ERROR,
                    true, e.getMessage());
        }
    }

    public void sendPostRequest(String url, Function<HttpResponse<String>, String> validator) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(BASE_URL + url))
                    .POST(HttpRequest.BodyPublishers.noBody())
                    .header("Accept", "*/*")
                    .header("User-Agent", "Thunder Client (https://www.thunderclient.com)")
                    .header("Content-Type", "application/json")
                    .header("Cookie", cookie)
                    .build();

            client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(this::getCookie)
                    .thenApply(validator)
                    .exceptionally(this.handleException);
        } catch (URISyntaxException e) {
            MainApp.router.CreatePopup("Error", "An error occurred while sending the request", Alert.AlertType.ERROR,
                    true, e.getMessage());
        }
    }
}
