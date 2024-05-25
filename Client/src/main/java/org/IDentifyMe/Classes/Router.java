package org.IDentifyMe.Classes;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Stack;
import java.util.function.Function;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Router implements Function<String, String> {
    private Stage stage;
    private Map<String, String> routes = new HashMap<>();
    private final String PATH = "/org/IDentifyMe/view/";
    private Stack<String> history;

    public Router(Stage stage) {
        this.stage = stage;
        this.history = new Stack<>();

        this.addRoute("login", "login.fxml");
        this.addRoute("about", "AboutPage.fxml");

        this.addRoute("studentHome", "StudentHome.fxml");
        this.addRoute("studentDashboard", "StudentDashboard.fxml");
        this.addRoute("studentProfile", "ProfilePage.fxml");
        this.addRoute("studentNewRequest", "requestFirstPage.fxml");
        this.addRoute("studentRequestStatus", "requestStatusPage.fxml");
        this.addRoute("studentRenewalRequest", "renewalRequestPage.fxml");
        this.addRoute("studentreplacementRequest", "replacementRequestPage.fxml");


        this.addRoute("financeHome", "FinanceHome.fxml");
        this.addRoute("financeDashboard", "FinanceDashboard.fxml");
        this.addRoute("financeProfile", "FinanceProfile.fxml");

        this.addRoute("ID_DepartmentHome", "ID_DepartmentHome.fxml");
        this.addRoute("ID_DepartmentDashboard", "ID_DepartmentDashboard.fxml");
        this.addRoute("ID_DepartmentProfile", "ID_DepartmentProfile.fxml");
        this.addRoute("calendar", "CalendarPage.fxml");
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
        } catch (Exception e) {
            e.printStackTrace();
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
            root.getChildren().add(FXMLLoader.load(getClass().getResource(routes.get(name))));
            return true;
        } catch (IOException e) {
            CreatePopup("Error", "An error occurred while navigating to the page", Alert.AlertType.ERROR, true,
                    e.getMessage());
        }
        return false;
    }

    public void start() {
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/org/IDentifyMe/view/login.fxml"))));
        } catch (Exception e1) {
            e1.printStackTrace();
            System.out.println("Error loading login.fxml");
            System.exit(1);
        }
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
        if (history.size() > 1 && !history.peek().equals("login")) {
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

    @Override
    public String apply(String arg0) {
        if (arg0 != null) {
            Platform.runLater(() -> {
                this.navigateTo(arg0);
            });
        }
        return null;
    }

    public void setBackground(String imageName, Pane bg) {
        BackgroundImage backgroundImage = new BackgroundImage(
                new Image(getClass().getResource("/org/IDentifyMe/image/" + imageName).toExternalForm(), 800, 600,
                        false,
                        true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true));
        bg.setBackground(new Background(backgroundImage));
    }

}
