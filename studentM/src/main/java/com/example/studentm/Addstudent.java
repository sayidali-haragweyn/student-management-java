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


public class Addstudent implements Initializable {

    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project" ,"root" ,"");
    Statement st = con.createStatement();

    @FXML
    private TableColumn<stcourse, String> Course;

    @FXML
    private TableColumn<stcourse, String> FnaME;

    @FXML
    private TableColumn<stcourse, String> Gender;

    @FXML
    private TableColumn<stcourse, String> LName;

    @FXML
    private TableColumn<stcourse, Integer> StID;

    @FXML
    private TableColumn<stcourse, Integer> Year;

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
    private FontAwesomeIcon logout;

    @FXML
    private Button minimize;

    @FXML
    private TableView<stcourse> tablestudent;
    ObservableList<stcourse> list = FXCollections.observableArrayList();

    @FXML
    private TextField txtcourse;

    @FXML
    private TextField txtfname;

    @FXML
    private TextField txtgender;

    @FXML
    private TextField txtlname;

    @FXML
    private TextField txtsaerch;

    @FXML
    private TextField txtstid;

    @FXML
    private TextField txtyaer;

    public Addstudent() throws SQLException {
    }
    @FXML
    void saerchhandler(ActionEvent event) throws SQLException {
        if (txtsaerch.getText().equals("")) {
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setContentText("PLZ fill the search id!");
            al.show();
        } else {
            tablestudent.getItems().clear();
            tablestudent.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            StID.setCellValueFactory(new PropertyValueFactory<stcourse, Integer>("stid"));
            FnaME.setCellValueFactory(new PropertyValueFactory<stcourse, String>("fname"));
            LName.setCellValueFactory(new PropertyValueFactory<stcourse, String>("lname"));
            Gender.setCellValueFactory(new PropertyValueFactory<stcourse, String>("gender"));
            Course.setCellValueFactory(new PropertyValueFactory<stcourse, String>("course"));
            Year.setCellValueFactory(new PropertyValueFactory<stcourse, Integer>("year"));
            int stid = Integer.parseInt(txtsaerch.getText());
            ResultSet rs = st.executeQuery("select * from students where stid ='" + stid + "'");
            while (rs.next()) {
                list.addAll(new stcourse(
                        rs.getInt("stid"),
                        rs.getString("fname"),
                        rs.getString("lname"),
                        rs.getString("gender"),
                        rs.getString("course"),
                        rs.getInt("year") ));

            }
            tablestudent.setItems(list);

        }

    }
    @FXML
    void addhandler(ActionEvent event) throws SQLException {
        if (txtstid.getText().equals("") || txtfname.getText().equals("") || txtlname.getText().equals("") || txtgender.getText().equals("") || txtcourse.getText().equals("") || txtyaer.getText().equals("")) {
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setContentText("PLZ FILL THE BALNKS!");
            al.show();
        } else {
            int id = Integer.parseInt(txtstid.getText());
            String fname = txtfname.getText();
            String lname = txtlname.getText();
            String gender = txtgender.getText();
            String course = txtcourse.getText();
            int year = Integer.parseInt(txtyaer.getText());
            st.executeUpdate("insert into students values('" + id + "', '" + fname + "' , '" + lname + "','" + gender + "','" + course + "','" + year + "')");
            Alert al = new Alert(Alert.AlertType.INFORMATION);
            al.setContentText("saved");
            al.show();
            sttable();
        }
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
    void deletehandler(ActionEvent event) throws SQLException {
        if (txtstid.getText().equals("") || txtfname.getText().equals("") || txtlname.getText().equals("") || txtgender.getText().equals("") || txtcourse.getText().equals("") || txtyaer.getText().equals("")) {
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setContentText("PLZ FILL THE BALNKS!");
            al.show();
        } else {
            int id = Integer.parseInt(txtstid.getText());
            String fname = txtfname.getText();
            String lname = txtlname.getText();
            String gender = txtgender.getText();
            String course = txtcourse.getText();
            int year = Integer.parseInt(txtyaer.getText());
            st.executeUpdate("delete from students where stid = '" + id + "'");
            Alert al = new Alert(Alert.AlertType.INFORMATION);
            al.setContentText("saved");
            al.show();
            sttable();
        }
    }
    @FXML
    void getdata(MouseEvent event) {
     stcourse stcourse = tablestudent.getSelectionModel().getSelectedItem();
     txtstid.setText(String.valueOf(stcourse.getStid()));
     txtfname.setText(stcourse.getFname());
     txtlname.setText(stcourse.getLname());
     txtgender.setText(stcourse.getGender());
     txtcourse.setText(stcourse.getCourse());
     txtyaer.setText(String.valueOf(stcourse.getYear()));
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
    void studenthandler(ActionEvent event) {

    }

    @FXML
    void updatehandler(ActionEvent event) throws SQLException {
        if (txtstid.getText().equals("") || txtfname.getText().equals("") || txtlname.getText().equals("") || txtgender.getText().equals("") || txtcourse.getText().equals("") || txtyaer.getText().equals("")) {
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setContentText("PLZ FILL THE BALNKS!");
            al.show();
        } else {
            int id = Integer.parseInt(txtstid.getText());
            String fname = txtfname.getText();
            String lname = txtlname.getText();
            String gender = txtgender.getText();
            String course = txtcourse.getText();
            int year = Integer.parseInt(txtyaer.getText());
            st.executeUpdate("Update students set fname ='" + fname + "',lname ='" + lname + "',gender ='" + gender + "',course ='" +course +"',year ='" +year +"' where stid = '" + id + "'");
            Alert al = new Alert(Alert.AlertType.INFORMATION);
            al.setContentText("saved");
            al.show();
            sttable();
    }}
    void sttable() throws SQLException {
        tablestudent.getItems().clear();
        tablestudent.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        StID.setCellValueFactory(new PropertyValueFactory<stcourse,Integer>("stid"));
        FnaME.setCellValueFactory(new PropertyValueFactory<stcourse,String>("fname"));
        LName.setCellValueFactory(new PropertyValueFactory<stcourse,String>("lname"));
        Gender.setCellValueFactory(new PropertyValueFactory<stcourse,String>("gender"));
        Course.setCellValueFactory(new PropertyValueFactory<stcourse,String>("course"));
        Year.setCellValueFactory(new PropertyValueFactory<stcourse,Integer>("year"));
        ResultSet rs = st.executeQuery("select * from students ");
        while (rs.next()){
            list.addAll(new stcourse(
                    rs.getInt ("stid"),
                    rs.getString("fname"),
                    rs.getString("lname"),
                    rs.getString("gender"),
                    rs.getString("course"),
                    rs.getInt ("year")

            ));
        }
        tablestudent.setItems(list);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            sttable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
