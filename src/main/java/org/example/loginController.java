package org.example;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.github.derickfelix.Jsqb;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import org.jloquent.Connector;

public class loginController {


    @FXML
    JFXTextField txtUsername;
    @FXML
    JFXPasswordField txtPassword;
    @FXML
    StackPane stackDialog;

    JFXDialogLayout content = new JFXDialogLayout();

    @FXML
    void attempt() throws SQLException, IOException {
        if (!this.txtUsername.getText().equals("") && !this.txtPassword.getText().equals("")){

            Connector connector = Connector.getInstance();
            Jsqb jsqb = new Jsqb();
            String whereClause =String.format("password='%s' AND nickname='%s'",this.txtPassword.getText(),
                    this.txtUsername.getText());
            String sql = jsqb.select("usuarios","count(id) as n").where(whereClause).write();
            System.out.println(sql);
            ResultSet c =connector.executeQuery(sql.replace("usuarios.",""));
            if (c.next()){
                if (c.getInt("n")>0){
                    App.setRoot("dashboard");
                }
            }
        }else{
            content.setHeading(new Text("Error"));
            content.setBody(new Text("la contraseña y/o el usuario no pueden estar vacios"));
            JFXDialog dialog=new JFXDialog(stackDialog, content, JFXDialog.DialogTransition.CENTER);
            dialog.show();
        }
    }
}
