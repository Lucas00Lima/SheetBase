module com.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires org.apache.poi.poi;

    opens com.example to javafx.fxml;

    exports com.example;
}
