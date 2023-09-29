package com.example.studentm;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class HelloController {
    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project" ,"root" ,"");
    Statement st = con.createStatement();
    // int rs = st.executeUpdate("insert into groupe values('root','root')");
    @FXML
    private Button btnclose;

    @FXML
    private Label lblresult;

    @FXML
    private Button btnlogin;

    @FXML
    private PasswordField txtpassword;

    @FXML
    private TextField txtusername;

    public HelloController() throws SQLException {
    }

    @FXML
    void closehandler(ActionEvent event) {
    System.exit(1);
    }

    @FXML
    void loginhandler(ActionEvent event) throws IOException, SQLException {
        if (txtusername.getText().equals("") || txtpassword.getText().equals("")) {
            lblresult.setText("plz fill the blanks!");
        } else {
            String user = txtusername.getText();
            String pass = txtpassword.getText();
            ResultSet rs = st.executeQuery("select * from groupe where username = '" + user + "' and password = '" + pass + "'");
            if (rs.next()) {
                Pane root = FXMLLoader.load(getClass().getResource("home.fxml"));
                Scene scene = new Scene(root, 1100, 603);
                Stage loginStage = new Stage();
                loginStage.setScene(scene);
                loginStage.setTitle("main window");
                loginStage.show();
                ((Node) (event.getSource())).getScene().getWindow().hide();

            } else {
                lblresult.setText("Wrong! InValid username and password");

            }

        }
    }
}