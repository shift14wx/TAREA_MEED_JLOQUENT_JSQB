package org.modelos;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import org.jloquent.Model;

public class Producto extends Model {


    SimpleStringProperty nombre;
    SimpleDoubleProperty precio;
    SimpleIntegerProperty unidades;

    public String getNombre() {
        return nombre.get();
    }

    public SimpleStringProperty nombreProperty() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre=new SimpleStringProperty(nombre);
    }

    public double getPrecio() {
        return precio.get();
    }

    public SimpleStringProperty precioProperty() {
        return new SimpleStringProperty(precio.getValue()+"");
    }

    public void setPrecio(double precio) {
        this.precio= new SimpleDoubleProperty(precio);
    }

    public int getUnidades() {
        return unidades.get();
    }

    public SimpleStringProperty unidadesProperty() {
        return new SimpleStringProperty(unidades.getValue()+"");
    }

    public void setUnidades(int unidades) {
        this.unidades = new SimpleIntegerProperty(unidades);
    }
}
