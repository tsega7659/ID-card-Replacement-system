package org.IDentifyMe.Classes;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Router {
    private Stage stage;
    private Map<String, String> routes = new HashMap<>();
    private final String PATH = "/org/IDentifyMe/view/";
    private Stack<String> history;
    private Stack<StringPanePair> navHistory;

    public Router(Stage stage) {
        this.stage = stage;
        this.history = new Stack<>();
    }

    public void addRoute(String name, String fxmlFile) {
        routes.put(name, PATH + fxmlFile);
    }

    public boolean navigateTo(String name) {
        try {
            if (!routes.containsKey(name)) {
                throw new IOException("The route does not exist");
            }
            history.push(name);
            Parent root = FXMLLoader.load(getClass().getResource(routes.get(name)));
            stage.setScene(new Scene(root));
            return true;
        } catch (IOException e) {
            CreatePopup("Error", "An error occurred while navigating to the page", Alert.AlertType.ERROR, true,
                    e.getMessage());
        }
        return false;
    }

    public boolean addViewTo(String name, Pane root) {
        try {
            if (!routes.containsKey(name)) {
                throw new IOException("The route does not exist");
            }
            history.push(name);
            Parent child = FXMLLoader.load(getClass().getResource(routes.get(name)));
            root.getChildren().add(child);
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
            StringPanePair pair = new StringPanePair(name, root);
            navHistory.push(pair);
            Parent child = FXMLLoader.load(getClass().getResource(routes.get(name)));
            root.getChildren().clear();
            root.getChildren().add(child);
            return true;
        } catch (IOException e) {
            CreatePopup("Error", "An error occurred while navigating to the page", Alert.AlertType.ERROR, true,
                    e.getMessage());
        }
        return false;
    }

    public boolean reloadView() {
        if (history.size() > 0) {
            StringPanePair map = navHistory.pop();
            return updateViewTo(map.getText(), map.getPane());
        }
        return false;
    }

    public boolean navigateViewBack() {
        if (history.size() > 1) {
            navHistory.pop();
            StringPanePair map = navHistory.peek();
            return updateViewTo(map.getText(), map.getPane());
        } else if (history.size() == 1) {
            return navigateTo(history.peek());
        }
        return false;
    }

    public boolean navigateBack() {
        if (history.size() > 1) {
            history.pop();
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
}

class StringPanePair {
    private String text;
    private Pane pane;

    public StringPanePair(String text, Pane pane) {
        this.text = text;
        this.pane = pane;
    }

    public String getText() {
        return text;
    }

    public Pane getPane() {
        return pane;
    }
}