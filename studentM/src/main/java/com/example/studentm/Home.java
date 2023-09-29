package com.example.studentm;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Home {

        @FXML
        private Button btncourses;

        @FXML
        private Button btngrade;

        @FXML
        private Button btnhome;

        @FXML
        private Button btnstudent;

        @FXML
        private Button close;

        @FXML
        private Label home_totalEnrolled;

        @FXML
        private Label home_totalFemale;

        @FXML
        private Label home_totalmale;

        @FXML
        private FontAwesomeIcon logout;

        @FXML
        private Button minimize;

        @FXML
        private BarChart<?, ?> totalenrolledchart;

        @FXML
        private AreaChart<?, ?> totalfemalechart;

        @FXML
        private LineChart<?, ?> totalmalecart;

        @FXML
        void close(ActionEvent event) {

        }

        @FXML
        void courseshandler(ActionEvent event) throws IOException {
                Pane root = FXMLLoader.load(getClass().getResource("course.fxml"));
                Scene scene = new Scene(root, 1100, 603);
                Stage loginStage = new Stage();
                loginStage.setScene(scene);
                loginStage.setTitle("main window");
                loginStage.show();
                ((Node) (event.getSource())).getScene().getWindow().hide();
        }

        @FXML
        void gradehandler(ActionEvent event) throws IOException {
                Pane root = FXMLLoader.load(getClass().getResource("grades.fxml"));
                Scene scene = new Scene(root, 1100, 603);
                Stage loginStage = new Stage();
                loginStage.setScene(scene);
                loginStage.setTitle("main window");
                loginStage.show();
                ((Node) (event.getSource())).getScene().getWindow().hide();
        }

        @FXML
        void homehandler(ActionEvent event) throws IOException {
                Pane root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
                Scene scene = new Scene(root, 600, 400);
                Stage loginStage = new Stage();
                loginStage.setScene(scene);
                loginStage.setTitle("main window");
                loginStage.show();
                ((Node) (event.getSource())).getScene().getWindow().hide();
        }

        @FXML
        void minimize(ActionEvent event) {

        }

        @FXML
        void studenthandler(ActionEvent event) throws IOException {
                Pane root = FXMLLoader.load(getClass().getResource("addstudent.fxml"));
                Scene scene = new Scene(root, 1100, 603);
                Stage loginStage = new Stage();
                loginStage.setScene(scene);
                loginStage.setTitle("main window");
                loginStage.show();
                ((Node) (event.getSource())).getScene().getWindow().hide();
        }

}
