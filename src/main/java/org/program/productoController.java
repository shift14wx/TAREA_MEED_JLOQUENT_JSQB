package org.program;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import org.modelos.Producto;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;



public class productoController implements Initializable {


    ObservableList<Producto> listProductos = FXCollections.observableArrayList();
    @FXML public TableView<Producto> productoTableView = new TableView<Producto>();
    @FXML public TableColumn<Producto,String> tcNombreProducto;
    @FXML public TableColumn<Producto, String> tcPrecioProducto;
    @FXML public TableColumn<Producto,String> tcUnidadesProducto;
    @FXML public StackPane stackDialog;
    Integer getProductoIndexTable = 0;

    @FXML
    Button btnEdit;
    @FXML
    Button btnDelete;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        this.btnEdit.setDisable(true);
        this.btnDelete.setDisable(true);

        new Thread(new Runnable() {
            public void run(){
                listProductos = FXCollections.observableArrayList(Producto.all(Producto::new));
                loadListInTable();
            }
        }).start();
        this.setTable();

        /*this.listProductos.addListener((InvalidationListener) observable ->{
            if (listProductos.size()>0){
                this.lblCargando.setVisible(false);
            }
        });*/
    }



    void setTable(){
        this.tcNombreProducto.setCellValueFactory(nombre->nombre.getValue().nombreProperty());
        this.tcPrecioProducto.setCellValueFactory(precio->precio.getValue().precioProperty());
        this.tcUnidadesProducto.setCellValueFactory(unidades->unidades.getValue().unidadesProperty());
        this.productoTableView.setItems(this.getListProductos());
    }

    public ObservableList<Producto> getListProductos(){
        return this.listProductos;
    }

    public void loadListInTable(){
        this.productoTableView.setItems(this.getListProductos());
        System.out.println("cargando");
    }


    @FXML
    void selectProductoFromTable(){

        try {
            this.getProductoIndexTable =this.productoTableView.getSelectionModel().getSelectedIndex();
            System.out.println("seleccionado Index "+this.getProductoIndexTable);
            this.btnEdit.setDisable(false);
            this.btnDelete.setDisable(false);
        }catch (Exception e){

        }
    }

    @FXML
    void agregarProducto() throws IOException {
        JFXDialogLayout content = new JFXDialogLayout();
        FXMLLoader Formulario = new FXMLLoader(getClass().getResource("/org.program/productoForm.fxml"));
        content.setHeading(new Text("Agregar Producto"));
        AnchorPane formularioAnchor = Formulario.load();
        productoFormController formularioController = Formulario.getController();
        content.setBody(formularioAnchor);
        System.out.println("sin problemas");
        JFXDialog dialog=new JFXDialog(stackDialog, content, JFXDialog.DialogTransition.CENTER);

        formularioController.btnAgregar.setOnMouseClicked(e->{
            if (formularioController.seAgrego>0){
                Producto productoRecibir;
                productoRecibir = formularioController.returnProducto();
                productoRecibir.save();
                listProductos.add(productoRecibir);
                formularioController.seAgrego=-1;
                dialog.close();
                content.setHeading(new Text("Producto agregado"));
                content.setBody(new Text("Se agrego correctamente!!"));
                JFXDialog dialogAlert=new JFXDialog(stackDialog, content, JFXDialog.DialogTransition.CENTER);
                dialogAlert.show();
            }
        });

        dialog.show();
    }

    @FXML
    void eliminarProducto() throws SQLException {
        JFXDialogLayout content = new JFXDialogLayout();
        if (this.getProductoIndexTable>-1){

            this.listProductos.get(getProductoIndexTable).delete();

            this.listProductos.remove(this.listProductos.get(getProductoIndexTable));
            //System.out.println(this.getProductoIndexTable);
            //System.out.println(this.listProductos.size());
            content.setHeading(new Text("ProductoEliminado Eliminado"));
            content.setBody(new Text("El Producto se ha eliminado correctamente"));
            JFXDialog dialogAlert=new JFXDialog(stackDialog, content, JFXDialog.DialogTransition.CENTER);
            this.getProductoIndexTable = -1;
            dialogAlert.show();

        }
        this.setButtons(false);
    }
    @FXML
    void editarProducto() throws IOException {

        if (this.getProductoIndexTable>-1) {

            JFXDialogLayout content = new JFXDialogLayout();
            FXMLLoader Formulario = new FXMLLoader(getClass().getResource("/org.program/productoForm.fxml"));
            content.setHeading(new Text("Editar Proveedor"));
            AnchorPane formularioAnchor = Formulario.load(); // PRIMERO DEBE DE CARGARSE EL FORMULARIO
            productoFormController formularioController = Formulario.getController(); // PARA DESPUES TOMAR EL CONTROLADOR
            content.setBody(formularioAnchor);
            formularioController.txtName.setText(this.listProductos.get(getProductoIndexTable).getNombre());
            formularioController.spPrecio.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0.01,
                    255.0,this.listProductos.get(getProductoIndexTable).getPrecio(),1.0));
            formularioController.spStock.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,
                    255,this.listProductos.get(getProductoIndexTable).getUnidades(),0));
            formularioController.idlbl.setText("Producto id:" + this.listProductos.get(getProductoIndexTable).getId());
            //formularioController.id = this.listProductos.get(getProductoIndexTable).getId();
            JFXDialog dialog = new JFXDialog(stackDialog, content, JFXDialog.DialogTransition.CENTER);
            formularioController.btnAgregar.setText("Actualizar");
            dialog.show();

            formularioController.btnAgregar.setOnMouseClicked(e -> {

                if (formularioController.seActualizo) { // se actualizo


                    Producto productoRecibir;
                    productoRecibir = formularioController.returnProducto();
                    productoRecibir.update();
                    this.listProductos.set(this.getProductoIndexTable,productoRecibir);
                    formularioController.seAgrego = -1;
                    formularioController.seActualizo = false;
                    dialog.close();
                    content.setHeading(new Text("Producto Actualizado"));
                    content.setBody(new Text("el Producto " + listProductos.get(this.getProductoIndexTable).getNombre() + " se " +
                            "ha actualizado correctamente!!"));
                    JFXDialog dialogAlert = new JFXDialog(stackDialog, content, JFXDialog.DialogTransition.CENTER);
                    dialogAlert.show();
                }

            });
        }

        }

        void setButtons(Boolean tipe){
        this.btnEdit.setDisable(tipe);
        this.btnDelete.setDisable(tipe);
    }
}
