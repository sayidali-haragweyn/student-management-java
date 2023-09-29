module com.example.studentm {
    requires javafx.controls;
    requires javafx.fxml;
    requires fontawesomefx;
    requires java.sql;


    opens com.example.studentm to javafx.fxml;
    exports com.example.studentm;
}