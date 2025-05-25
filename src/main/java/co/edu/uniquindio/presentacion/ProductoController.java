package co.edu.uniquindio.presentacion;

import co.edu.uniquindio.App;
import co.edu.uniquindio.aplicacion.exceptions.ValidacionException;
import co.edu.uniquindio.aplicacion.producto.ProductoService;
import co.edu.uniquindio.dominio.producto.Producto;
import co.edu.uniquindio.presentacion.formulario.CrearProductoForm;
import co.edu.uniquindio.presentacion.validador.CrearProductoFormValidador;
import co.edu.uniquindio.utilities.AlertUtility;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ProductoController {
    private ProductoService service;

    @FXML
    private TableColumn<Producto, Integer> idCol;

    @FXML
    private TableColumn<Producto, String> caracteristicasCol;

    @FXML
    private TextArea caracteristicasTF;

    @FXML
    private TableColumn<Producto, String> catPrincipalCol;

    @FXML
    private TableColumn<Producto, String> catSecundariaCol;

    @FXML
    private TextField categoriaPrincipalTF;

    @FXML
    private TextField categoriaSecundariaTF;

    @FXML
    private TableColumn<Producto, String> codigoCol;

    @FXML
    private TextField codigoTF;

    @FXML
    private Button crearProductoBtn;

    @FXML
    private TableColumn<Producto, String> descripcionCol;

    @FXML
    private TextArea descripcionTF;

    @FXML
    private Button limpiarCamposBtn;

    @FXML
    private TextField nombreTF;

    @FXML
    private TableColumn<Producto, Float> precioCol;

    @FXML
    private TextField precioTF;

    @FXML
    private Button regresarBtn;

    @FXML
    private TableColumn<Producto, Integer> stockCol;

    @FXML
    private TextField stockTF;

    @FXML
    private TableColumn<Producto, String> nombreCol;

    @FXML
    private TableView<Producto> tablaProductos;

    @FXML
    private TextField buscarProductoTF;
    
    public ProductoController(ProductoService service) {
        this.service = service;
    }

    @FXML
    void initialize() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        codigoCol.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        nombreCol.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        catPrincipalCol.setCellValueFactory(new PropertyValueFactory<>("categoriaPrincipal"));
        catSecundariaCol.setCellValueFactory(new PropertyValueFactory<>("categoriaSecundaria"));
        precioCol.setCellValueFactory(new PropertyValueFactory<>("precio"));
        stockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        descripcionCol.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        caracteristicasCol.setCellValueFactory(new PropertyValueFactory<>("caracteristicas"));

        actualizarTabla();
    }

    @FXML
    void crearProducto(ActionEvent event) {
        CrearProductoForm form = new CrearProductoForm(
            codigoTF.getText().trim(),
            nombreTF.getText().trim(),
            precioTF.getText().trim(),
            stockTF.getText().trim(),
            descripcionTF.getText().trim(),
            categoriaPrincipalTF.getText().trim(),
            categoriaSecundariaTF.getText().trim(),
            caracteristicasTF.getText().trim()
        );

        try {
            new CrearProductoFormValidador().validar(form);
            service.crear(form.toData());
        } catch (ValidacionException e) {
            AlertUtility.validacion(e);
        }

        actualizarTabla();
    }

    @FXML
    void limpiarCampos(ActionEvent event) {
        codigoTF.clear();
        nombreTF.clear();
        categoriaPrincipalTF.clear();
        categoriaSecundariaTF.clear();
        precioTF.clear();
        stockTF.clear();
        descripcionTF.clear();
        caracteristicasTF.clear();
    }

    @FXML
    void regresar(ActionEvent event) throws Exception {
        App.setRoot("main");
    }

    @FXML
    void buscarProducto() {
        tablaProductos.setItems(FXCollections.observableList(
            service.hallarPorNombre(buscarProductoTF.getText().trim())
        ));
    }

    private void actualizarTabla() {
        buscarProductoTF.clear();

        tablaProductos.setItems(
            FXCollections.observableArrayList(service.hallarTodos())
        );
    }
}