package com.example.studentm;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Course implements Initializable {

    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project" ,"root" ,"");
    Statement st = con.createStatement();

    @FXML
    private TableColumn<student, String> Degreeclmn;

    @FXML
    private Button btnadd;

    @FXML
    private Button btncourses;

    @FXML
    private Button btndelete;

    @FXML
    private Button btngrade;

    @FXML
    private Button btnhome;

    @FXML
    private Button btnstudent;

    @FXML
    private Button btnupdate;

    @FXML
    private Button close;

    @FXML
    private TableColumn<student, String> courseclmn;

    @FXML
    private TableView<student> coursetable;
    ObservableList<student> list = FXCollections.observableArrayList();

    @FXML
    private TableColumn<student, String> descriptionclm;

    @FXML
    private FontAwesomeIcon logout;

    @FXML
    private Button minimize;

    @FXML
    private TextField txtcourse1;
    @FXML
    private Button btnrfresh;
    @FXML
    private TextField txtdegree;

    @FXML
    private TextField txtdescription;

    public Course() throws SQLException {
    }

    @FXML
    void addhandler(ActionEvent event) throws SQLException {

        if (txtcourse1.getText().equals("") || txtdescription.getText().equals("") || txtdegree.getText().equals("")) {
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setContentText("PLZ FILL THE BALNKS!");
            al.show();
        } else {
            String course = txtcourse1.getText();
            String description = txtdescription.getText();
            String degree = txtdegree.getText();
            st.executeUpdate("insert into course values('" + course + "', '" + description + "' , '" + degree + "')");
            Alert al = new Alert(Alert.AlertType.INFORMATION);
            al.setContentText("saved");
            al.show();
        bindtableview();
        }
    }

    @FXML
    void close(ActionEvent event) {

    }
    @FXML
    void refreshhandler(ActionEvent event) throws SQLException {
     bindtableview();
    }

    @FXML
    void courseshandler(ActionEvent event) {

    }

    @FXML
    void deletehandler(ActionEvent event) throws SQLException {
        if (txtcourse1.getText().equals("") || txtdescription.getText().equals("") || txtdegree.getText().equals("")) {
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setContentText("PLZ FILL THE BALNKS!");
            al.show();
        } else {
            String course = txtcourse1.getText();
            String description = txtdescription.getText();
            String degree = txtdegree.getText();
            st.executeUpdate("delete from  course  where degree ='" + degree + "'");
            Alert al = new Alert(Alert.AlertType.INFORMATION);
            al.setContentText("deleted");
            al.show();
            bindtableview();
        }
    }
    @FXML
    void getcousedata(MouseEvent event) {
        student student = coursetable.getSelectionModel().getSelectedItem();
        txtcourse1.setText(student.getCourse());
        txtdescription.setText(student.getDescription());
        txtdegree.setText(student.getDegree());

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

    @FXML
    void updatehandler(ActionEvent event) throws SQLException {
        String course = txtcourse1.getText();
        String description = txtdescription.getText();
        String degree = txtdegree.getText();


// Execute the UPDATE query
        int rs = st.executeUpdate("UPDATE course SET course = '" + course + "', description = '" + description + "' WHERE degree = '" + degree + "'");

// Check if any rows were updated
        if (rs > 0) {
            // Display a success message
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Update Successful");
            alert.setHeaderText("Course Updated");
            alert.setContentText("The course has been updated successfully.");
            alert.showAndWait();
        } else {
            // Display an error message
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Update Failed");
            alert.setHeaderText("Course Not Found");
            alert.setContentText("The course could not be found. Please check the degree and try again.");
            alert.showAndWait();

        }
        bindtableview();
        }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            bindtableview();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    void  bindtableview() throws SQLException {
        coursetable.getItems().clear();
        coursetable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        courseclmn.setCellValueFactory(new PropertyValueFactory<student,String>("course"));
        descriptionclm.setCellValueFactory(new PropertyValueFactory<student,String>("description"));
        Degreeclmn.setCellValueFactory(new PropertyValueFactory<student,String>("degree"));
        ResultSet rs = st.executeQuery("select * from course ");
        while (rs.next()){
            list.addAll(new student(
                    rs.getString("course"),
                    rs.getString("description"),
                    rs.getString("degree")
            ));
        }
        coursetable.setItems(list);




    }
}
