package org.modelos;

import javafx.beans.property.SimpleStringProperty;
import org.jloquent.Field;
import org.jloquent.Model;

public class Usuario extends Model {

    public void Usuario(){

    }

    public SimpleStringProperty nombre;
    public SimpleStringProperty nickname;

    public String getNickname() {
        return nickname.get();
    }

    public SimpleStringProperty nicknameProperty() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = new SimpleStringProperty(nickname);
    }

    public String getPassword() {
        return password.get();
    }

    public SimpleStringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password= new SimpleStringProperty(password);
    }

    public SimpleStringProperty password;

    public String getNombre() {
        return nombre.get();
    }

    public SimpleStringProperty nombreProperty() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre=new SimpleStringProperty(nombre);
    }
}
