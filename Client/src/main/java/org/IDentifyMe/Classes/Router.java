package org.IDentifyMe.Classes;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Stack;

import org.IDentifyMe.MainApp;

public class Router {
    private Stage stage;
    private Map<String, String> routes = new HashMap<>();
    private final String PATH = "/org/IDentifyMe/view/";
    private Stack<String> history;

    public Router(Stage stage) {
        this.stage = stage;
        this.history = new Stack<>();

        this.addRoute("login", "login.fxml");
        this.addRoute("studentDashboard", "StudentDashboard.fxml");
        this.addRoute("studentHome", "StudentHome.fxml");
        this.addRoute("financeHome", "FinanceHome.fxml");
        this.addRoute("ID_DepartmentHome", "ID_DepartmentHome.fxml");
        this.addRoute("about", "AboutPage.fxml");
    }

    public void addRoute(String name, String fxmlFile) {
        routes.put(name, PATH + fxmlFile);
    }

    public boolean navigateTo(String name) {
        try {
            if (!routes.containsKey(name)) {
                throw new IOException("The route does not exist");
            }
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource(routes.get(name)))));
            history.push(name);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            CreatePopup("Error", "An error occurred while navigating to the page", Alert.AlertType.ERROR, true,
                    e.getMessage());
        }
        return false;
    }

    public boolean logout() {
        Platform.runLater(() -> {
            MainApp.router.CreatePopup("Success", "Successful!",
                    Alert.AlertType.INFORMATION, false, "");
            ;
            try {
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource(PATH + "login.fxml"))));
            } catch (IOException e) {
                e.printStackTrace();
                CreatePopup("Error", "An error occurred while navigating to the page", Alert.AlertType.ERROR, true,
                        e.getMessage());
            }
            MainApp.User = null;
            HttpClientHandler.clearCookie();
        });
        return false;
    }

    public boolean addViewTo(String name, Pane root) {
        try {
            if (!routes.containsKey(name)) {
                throw new IOException("The route does not exist");
            }
            root.getChildren().add(FXMLLoader.load(getClass().getResource(routes.get(name))));
            return true;
        } catch (IOException e) {
            CreatePopup("Error", "An error occurred while navigating to the page", Alert.AlertType.ERROR, true,
                    e.getMessage());
        }
        return false;
    }

    public boolean updateViewTo(String name, Pane root) {
        try {
            if (!routes.containsKey(name)) {
                throw new IOException("The route does not exist");
            }
            root.getChildren().clear();
            root.getChildren().add(FXMLLoader.load(getClass().getResource(routes.get(name))));
            return true;
        } catch (IOException e) {
            CreatePopup("Error", "An error occurred while navigating to the page", Alert.AlertType.ERROR, true,
                    e.getMessage());
        }
        return false;
    }

    public boolean navigateBack() {
        if (history.size() > 1) {
            history.pop();
            System.out.println(history.peek());
            return navigateTo(history.peek());
        }
        return false;
    }

    public boolean reloadPage() {
        if (history.size() > 0) {
            return navigateTo(history.pop());
        }
        return false;
    }

    public void CreatePopup(String title, String message, Alert.AlertType type, boolean wait, String content) {
        Platform.runLater(() -> {
            Alert alert = new Alert(type);
            alert.setTitle(title);
            alert.setHeaderText(message);
            alert.setContentText(content);
            if (wait) {
                alert.showAndWait();
            } else {
                alert.show();
            }
        });
    }

    public boolean CreateCONFIRMATION(String title, String message, String content) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(message);
        alert.setContentText(content);
        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }
}
