package org.program;

import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import org.example.App;
import org.modelos.Usuario;

import java.net.URL;
import java.util.ResourceBundle;

public class yoController implements Initializable {

    @FXML
    StackPane stackDialog = new StackPane();
    @FXML
    JFXTextField txtNombre;
    @FXML
    JFXTextField txtNickname;
    @FXML
    JFXPasswordField txtPassword;

    Usuario usuario =App.getUsuarioLogueado();

    JFXDialogLayout content = new JFXDialogLayout();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.txtNombre.setText(usuario.getNombre());
        this.txtNickname.setText(usuario.getNickname());
        this.txtPassword.setText(usuario.getPassword());
    }


    @FXML
    void editarUsuario(){
        if (!this.txtNombre.getText().equals("") && !this.txtNickname.getText().equals("") && !this.txtPassword.getText().equals("")){
            usuario.setNombre(this.txtNombre.getText());
            usuario.setNickname(this.txtNickname.getText());
            usuario.setPassword(this.txtPassword.getText());
            usuario.update();
            App.setUsuarioLog(usuario);
            content.setHeading(new Text("Usuario Actualizado"));
            content.setBody(new Text("Usuario actualizado correctamente"));

        }else{
            content.setHeading(new Text("Error"));
            content.setBody(new Text("No puede dejar campos vacios!!!"));
        }

        JFXDialog dialog=new JFXDialog(stackDialog, content, JFXDialog.DialogTransition.CENTER);
        dialog.show();

    }
}
