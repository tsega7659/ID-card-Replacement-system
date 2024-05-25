package org.IDentifyMe.Classes;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.function.Function;

import javafx.scene.control.Alert;

import org.IDentifyMe.MainApp;
import org.json.JSONObject;

public class HttpClientHandler {
    private HttpClient client = HttpClient.newBuilder().build();
    private final String BASE_URL = "http://localhost:8081";
    private static String cookie = "";


    public void sendGetRequest(String url, Function<HttpResponse<String>, String> validator) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(BASE_URL + url))
                    .GET()
                    .header("Accept", "*/*")
                    .header("Content-Type", "application/json")
                    .header("Cookie", cookie)
                    .build();

            client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(this::getCookie)
                    .thenApply(validator)
                    .thenApply(MainApp.router)
                    .exceptionally(validatorFuctory.handleExceptionValidator());
        } catch (URISyntaxException e) {
            MainApp.router.CreatePopup("Error", "An error occurred while sending the request", Alert.AlertType.ERROR,
                    true, e.getMessage());
        }
    }

    public String sendGetRequest(String url) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(BASE_URL + url))
                    .GET()
                    .header("Accept", "*/*")
                    .header("Content-Type", "application/json")
                    .header("Cookie", cookie)
                    .build();
            JSONObject json = new JSONObject(client.send(request, HttpResponse.BodyHandlers.ofString()).body());
            if (json.getString("status").toLowerCase().equals("successful")) {
                return json.getString("message");
            } else {
                MainApp.router.navigateBack();
                MainApp.router.CreatePopup("Error", "An error occurred while sending the request", Alert.AlertType.ERROR,
                        true, json.getString("message"));
                
            }
        } catch (URISyntaxException e) {
            MainApp.router.navigateBack();
            MainApp.router.CreatePopup("Error", "An error occurred while sending the request", Alert.AlertType.ERROR,
                    true, e.getMessage());
        } catch (Exception e) {
            MainApp.router.navigateBack();
            MainApp.router.CreatePopup("Error", "An error occurred while sending the request", Alert.AlertType.ERROR,
                    true, e.getMessage());
        }
        return null;
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
                    .header("Content-Type", "application/json")
                    .header("Cookie", cookie)
                    .build();

            client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(this::getCookie)
                    .thenApply(validator)
                    .thenApply(MainApp.router)
                    .exceptionally(validatorFuctory.handleExceptionValidator());
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
                    .header("Content-Type", "application/json")
                    .header("Cookie", cookie)
                    .build();

            client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(this::getCookie)
                    .thenApply(validator)
                    .thenApply(MainApp.router)
                    .exceptionally(validatorFuctory.handleExceptionValidator());
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
                    .header("Content-Type", "application/json")
                    .header("Cookie", cookie)
                    .build();

            client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(this::getCookie)
                    .thenApply(validator)
                    .thenApply(MainApp.router)
                    .exceptionally(validatorFuctory.handleExceptionValidator());
        } catch (URISyntaxException e) {
            MainApp.router.CreatePopup("Error", "An error occurred while sending the request", Alert.AlertType.ERROR,
                    true, e.getMessage());
        }
    }
    public static void clearCookie(){
        cookie = "";
    }
}
