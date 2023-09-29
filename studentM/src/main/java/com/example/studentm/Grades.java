package com.example.studentm;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.xml.transform.Result;
import java.io.IOException;
import java.sql.*;

public class Grades {
    @FXML
    private TableColumn<results, Integer> colavg;

    @FXML
    private TableColumn<results, Integer> colcsharp;

    @FXML
    private TableColumn<results, String> colgrade;

    @FXML
    private TableColumn<results, Integer> coljava;

    @FXML
    private TableColumn<results, Integer> colreact;

    @FXML
    private TableColumn<results, Integer> coltotal;
    @FXML
    private TableColumn<results, String> colname;
    @FXML
    private TableView<results> tableresults;
    private ObservableList<results> data;

    public void initialize() {
        // Initialize the columns of the TableView with the appropriate property values
        colreact.setCellValueFactory(new PropertyValueFactory<results,Integer>("react"));
        coljava.setCellValueFactory(new PropertyValueFactory<results,Integer>("java"));
        colcsharp.setCellValueFactory(new PropertyValueFactory<results,Integer>("csharp"));
        coltotal.setCellValueFactory(new PropertyValueFactory<results,Integer>("total"));
        colavg.setCellValueFactory(new PropertyValueFactory<results,Integer>("avg"));
        colgrade.setCellValueFactory(new PropertyValueFactory<results,String>("grade"));
        colname.setCellValueFactory(new PropertyValueFactory<results,String>("name"));

        // Initialize the data list
        data = FXCollections.observableArrayList();

        // Populate the TableView with data from the database
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project" ,"root" ,"");
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM results");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int  react = rs.getInt ("react");
                int java = rs.getInt("java");
                int csharp = rs.getInt("csharp");
                int total = rs.getInt("total");
                int avg = rs.getInt("avg");
                String grade = rs.getString("grade");
                String name = rs.getString("name");
                data.add(new results(react, java, csharp, total, avg, grade,name) {

                });
            }
            tableresults.setItems(data);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    @FXML
    void getdata(MouseEvent event) {

    }
    @FXML
    private Button btncalculate;

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
    private Label lblavg;

    @FXML
    private Label lblgrade;

    @FXML
    private Label lbltotal;

    @FXML
    private FontAwesomeIcon logout;

    @FXML
    private Button minimize;

    @FXML
    private TextField txtcsharp;

    @FXML
    private TextField txtjava;

    @FXML
    private TextField txtreact;

    @FXML
    private TextField txtsname;

    @FXML
    void calculatehandler(ActionEvent event) {
     int react,java,csharp,total,avg;
     String grade;
     String name;
     react = Integer.parseInt(txtreact.getText());
     java = Integer.parseInt(txtjava.getText());
     csharp = Integer.parseInt(txtcsharp.getText());
     name = txtsname.getText();

     total = react + java + csharp;
     avg = total/3;
     if(avg >=85){
         grade = "A";
     } else if (avg >=75) {
         grade = "B";

     }
     else if (avg >=65) {
        grade = "c";

    }
     else if (avg >=45) {
        grade = "";

    }
     else {
         grade ="F";
     }
     lbltotal.setText(String.valueOf(total));
     lblavg.setText(String.valueOf(avg));
     lblgrade.setText(grade);
        // Store the data into the database
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project" ,"root" ,"");
            PreparedStatement stmt = con.prepareStatement("INSERT INTO results (react, java, csharp, total, avg, grade,name) VALUES (?, ?, ?, ?, ?, ?,?)");
            stmt.setInt(1, react);
            stmt.setInt(2, java);
            stmt.setInt(3,csharp);
            stmt.setInt(4, total);
            stmt.setInt(5, avg);
            stmt.setString(6, grade);
            stmt.setString(7, name);
            int rowsAffected = stmt.executeUpdate();
            System.out.println(rowsAffected + " row(s) affected.");
            data.add(new results(react, java, csharp, total, avg, grade,name));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        // Update the TableView with the new data
        tableresults.setItems(data);
    }



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
    void gradehandler(ActionEvent event) {

    }

    @FXML
    void homehandler(ActionEvent event) throws IOException {

        Pane root = FXMLLoader.load(getClass().getResource("home.fxml"));
        Scene scene = new Scene(root, 1100, 603);
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
