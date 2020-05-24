package org.modelos;

import javafx.beans.property.SimpleStringProperty;
import org.jloquent.Model;

import java.util.List;
import java.util.function.Supplier;

public class Producto extends Model {


    SimpleStringProperty idProducto;
    SimpleStringProperty nombre;
    SimpleStringProperty precio;
    SimpleStringProperty unidades;
    public Producto(String id, String nombre, String precio,
                    String unidades) {
        this.idProducto = new SimpleStringProperty(id);
        this.nombre = new SimpleStringProperty(nombre);
        this.precio = new SimpleStringProperty(precio);
        this.unidades = new SimpleStringProperty(unidades);
    }

    public Producto(){

    };

    public String getidProducto() {
        return idProducto.get();
    }

    public SimpleStringProperty idProductoProperty() {
        return idProducto;
    }

    public void setIdProducto(String id) {
        this.idProducto.set(id);
    }

    public String getNombre() {
        return nombre.get();
    }

    public SimpleStringProperty nombreProperty() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public String getPrecio() {
        return precio.get();
    }

    public SimpleStringProperty precioProperty() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio.set(precio);
    }

    public String getUnidades() {
        return unidades.get();
    }

    public SimpleStringProperty unidadesProperty() {
        return unidades;
    }

    public void setUnidades(String unidades) {
        this.unidades.set(unidades);
    }

}
