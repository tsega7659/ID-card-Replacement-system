module org.IDentifyMe {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires atlantafx.base;
    requires org.json;
    requires javafx.web;
    requires javafx.graphics;
    requires com.calendarfx.view;
    requires javafx.base;  


    opens org.IDentifyMe.Controller to javafx.fxml;
    opens org.IDentifyMe to javafx.fxml, javafx.graphics, org.IDentifyMe.Classes;
    opens org.IDentifyMe.Classes to javafx.graphics;
}