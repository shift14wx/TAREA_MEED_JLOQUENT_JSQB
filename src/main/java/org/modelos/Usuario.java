package org.modelos;

import javafx.beans.property.SimpleStringProperty;
import org.jloquent.Field;
import org.jloquent.Model;

public class Usuario extends Model {


    public SimpleStringProperty nombre;

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
