module org.IDentifyMe {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires atlantafx.base;
    requires org.json;


    opens org.IDentifyMe.Controller to javafx.fxml;
    exports org.IDentifyMe.Controller;
    opens org.IDentifyMe to javafx.fxml;
    exports org.IDentifyMe to javafx.graphics;
}