package org.program;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import org.modelos.Producto;

import java.net.URL;
import java.util.ResourceBundle;

public class productoFormController implements Initializable {


    public int id = 0;
    @FXML
    public Label idlbl = new Label();
    @FXML
    public Button btnAgregar = new Button();
    @FXML
    public JFXTextField txtName = new JFXTextField();
    @FXML
    public Spinner<Double> spPrecio = new Spinner<Double>();
    @FXML
    public Spinner<Integer> spStock = new Spinner<Integer>();

    Producto producto = new Producto();

    Integer seAgrego=0;
    Boolean seActualizo = false;



    public void setSpinners(){
        this.spPrecio.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0.01,255.0,1.0,1.0));
        this.spStock.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1,255,1,1));
    }
    @FXML
    void agregarProducto(){
        if (!this.txtName.getText().equals("") ){
            this.producto.setNombre(this.txtName.getText());
            this.producto.setPrecio(this.spPrecio.getValue());
            this.producto.setUnidades(this.spStock.getValue());
            this.seAgrego = 1;
            this.seActualizo=true;
        }
    }

     Producto returnProducto(){
        return this.producto;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.setSpinners();
    }
}
