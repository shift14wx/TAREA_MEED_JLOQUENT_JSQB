package org.example;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

public class dashboardController implements Initializable {

    @FXML
AnchorPane mainStage = new AnchorPane();
    @FXML
    private void logout() throws IOException {
        App.setRoot("login");
    }

    @FXML
    void showProductosStage() throws IOException {
        try {
            StackPane usersAnchor = FXMLLoader.load(getClass().getResource("/org.program/productoMain.fxml"));
            this.mainStage.getChildren().setAll(usersAnchor);
            //new FadeInRight(this.mainStage).play();
        }catch (Exception e){
            throw e;
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            AnchorPane usersAnchor = FXMLLoader.load(getClass().getResource("/org.program/index.fxml"));
            this.mainStage.getChildren().setAll(usersAnchor);
            //new FadeInRight(this.mainStage).play();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}