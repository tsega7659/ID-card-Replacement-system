package org.IDentifyMe.Classes;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletionException;
import java.util.function.Function;

import org.IDentifyMe.MainApp;
import org.json.JSONObject;

import javafx.scene.control.Alert;

public class validatorFuctory {

    public static Function<HttpResponse<String>, String> createValidator(String nextPage) {
        return (response) -> {
            if (response.statusCode() == 200) {
                JSONObject json = new JSONObject(response.body());
                if (json.getString("status").toLowerCase().equals("successful")) {
                    MainApp.router.CreatePopup("Success", "Successful!",
                            Alert.AlertType.INFORMATION, false, json.getString("message"));
                    return nextPage;
                }
            } else {
                MainApp.router.CreatePopup("Error", "An error occurred while sending the request",
                        Alert.AlertType.ERROR, true, response.statusCode() + "");
            }
            return null;
        };
    }

    public static Function<HttpResponse<String>, String> createValidator(String nextPage, String status, String code) {
        return (response) -> {
            if (response.statusCode() == 200) {
                JSONObject json = new JSONObject(response.body());
                if (json.getString(status).toLowerCase().equals(code)) {
                    MainApp.router.CreatePopup("Success", "Successful!",
                            Alert.AlertType.INFORMATION, false, json.getString("message"));
                    return nextPage;
                }
            } else {
                MainApp.router.CreatePopup("Error", "An error occurred while sending the request",
                        Alert.AlertType.ERROR, true, response.statusCode() + "");
            }
            return null;
        };
    }
    public static Function<Throwable, String> handleExceptionValidator() {
        return (error) -> {
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
}
}
