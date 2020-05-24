package org.program;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.example.App;
import org.jloquent.Connector;
import org.modelos.Producto;
import org.modelos.Usuario;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class productoController implements Initializable {

    ObservableList<Producto> listProductos;
    @FXML public TableView<Producto> productoTableView = new TableView<Producto>();
    @FXML public TableColumn<Producto, String> tcIdProducto;
    @FXML public TableColumn<Producto,String> tcNombreProducto;
    @FXML public TableColumn<Producto,String> tcPrecioProducto;
    @FXML public TableColumn<Producto,String> tcUnidadesProducto;


    void setTable(){
        this.listProductos =FXCollections.observableArrayList();
        this.tcIdProducto.setCellValueFactory(idProducto->idProducto.getValue().idProductoProperty());
        this.tcNombreProducto.setCellValueFactory(nombre->nombre.getValue().idProductoProperty());
        this.tcPrecioProducto.setCellValueFactory(precio->precio.getValue().idProductoProperty());
        this.tcUnidadesProducto.setCellValueFactory(unidades->unidades.getValue().idProductoProperty());
        this.productoTableView.setItems(this.getListProductos());
    }

    public ObservableList<Producto> getListProductos(){
        return this.listProductos;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new Thread(new Runnable() {
            public void run(){
                Producto.all(Producto::new);
                //listProductos = FXCollections.observableArrayList(Producto.all(Producto::new));
            }
        }).start();
        this.setTable();
    }
}
