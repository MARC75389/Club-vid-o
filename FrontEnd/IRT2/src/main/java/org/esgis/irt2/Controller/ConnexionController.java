package org.esgis.irt2.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ConnexionController{
    @FXML
    private TextField txtUsername; // Assurez-vous d'ajouter fx:id="txtUsername" dans le FXML
    @FXML
    private TextField txtPassword; // Assurez-vous d'ajouter fx:id="txtPassword" dans le FXML

    @FXML
    protected void onHelloButtonClick(ActionEvent event) {
        String user = txtUsername.getText();
        String pass = txtPassword.getText();
        // Ici, vous ferez plus tard la vérification avec votre base de données (User.java)
        System.out.println("Tentative de connexion : " + user);

            }
        }
