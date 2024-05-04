package org.IDentifyMe;


import org.IDentifyMe.Classes.Router;
import atlantafx.base.theme.PrimerDark;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MainApp extends Application {
    public static final String APP_NAME = "IDentifyMe";
    public static Router router;
    public static String User;

    @Override
    public void start(Stage stage) {

        MainApp.setUserAgentStylesheet(new PrimerDark().getUserAgentStylesheet());

        router = new Router(stage);
        router.addRoute("login", "login.fxml");
        router.addRoute("studentDashboard", "StudentDashboard.fxml");
        router.addRoute("studentHome", "StudentHome.fxml");
        router.addRoute("financeHome", "FinanceHome.fxml");
        router.addRoute("ID_DepartmentHome", "ID_DepartmentHome.fxml");

        stage.setOnCloseRequest(e -> {
            e.consume();
            if(router.CreateCONFIRMATION("Exit", "Are you sure you want to exit?", null)){
                System.exit(0);
            }
        });
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/org/IDentifyMe/view/login.fxml"))));
        } catch (Exception e1) {
            e1.printStackTrace();
            System.out.println("Error loading login.fxml");
            System.exit(1);
        }
        
        stage.getIcons().add(new Image(getClass().getResource("/org/IDentifyMe/icons/icon.png").toExternalForm()));
        stage.centerOnScreen();
        stage.setTitle(APP_NAME);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
