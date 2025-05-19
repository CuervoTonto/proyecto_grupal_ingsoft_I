package co.edu.uniquindio.presentacion;

import java.io.IOException;

import org.thymeleaf.context.Context;

import co.edu.uniquindio.App;
import co.edu.uniquindio.aplicacion.carro.CarroComprasService;
import co.edu.uniquindio.aplicacion.detalleproducto.DetalleProductoService;
import co.edu.uniquindio.aplicacion.email.EmailService;
import co.edu.uniquindio.dominio.carro.CarroCompras;
import co.edu.uniquindio.dominio.detalleproducto.DetalleProducto;
import co.edu.uniquindio.dominio.mail.Email;
import co.edu.uniquindio.utilities.ThymeleafUtility;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class CarroCompraValidacionController {
    private CarroComprasService carroComprasService;

    private DetalleProductoService detalleProductoService;

    private CarroCompras carroCompras;
    
    private EmailService emailService;

    @FXML
    private TableColumn<DetalleProducto, Integer> cantidadCol;

    @FXML
    private TableColumn<DetalleProducto, String> conceptoCol;

    @FXML
    private TableColumn<DetalleProducto, Integer> idCol;

    @FXML
    private TableColumn<DetalleProducto, String> impuestoCol;

    @FXML
    private TableColumn<DetalleProducto, Integer> montoCol;

    @FXML
    private TableColumn<DetalleProducto, Float> subtotalCol;

    @FXML
    private TableView<DetalleProducto> tablaDetalleProductos;

    @FXML
    private TextField carroCodigoTF;

    @FXML
    private TextArea carroDetalleTF;

    @FXML
    private TextField carroFechaTF;

    @FXML
    private TextField carroHoraTF;

    @FXML
    private TextField carroIdTF;

    @FXML
    private TextField carroImpuestoTF;

    @FXML
    private TextArea carroObservacionesTF;

    @FXML
    private TextField carroSubtotalTF;

    @FXML
    private TextField carroTotalTF;

    @FXML
    private TextField ciudadanoCedulaTF;

    @FXML
    private TextField ciudadanoEmailTF;

    @FXML
    private TextField ciudadanoIdTF;

    @FXML
    private TextField ciudadanoTelefonoTF;

    public CarroCompraValidacionController(
        CarroComprasService carroComprasService,
        DetalleProductoService detalleProductoService,
        EmailService emailService
    ) {
        this.carroComprasService = carroComprasService;
        this.detalleProductoService = detalleProductoService;
        this.emailService = emailService;
    }

    @FXML
    void initialize() {
        // tablas y columnas
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        conceptoCol.setCellValueFactory(new PropertyValueFactory<>("concepto"));
        impuestoCol.setCellValueFactory(new PropertyValueFactory<>("impuesto"));
        cantidadCol.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        subtotalCol.setCellValueFactory(new PropertyValueFactory<>("subtotal"));
        montoCol.setCellValueFactory(new PropertyValueFactory<>("monto"));

        actualizarTablaDetalleProductos();

        // campos ciudadano
        ciudadanoIdTF.setText(carroCompras.getDueño().getId().toString());
        ciudadanoCedulaTF.setText(carroCompras.getDueño().getCedula());
        ciudadanoEmailTF.setText(carroCompras.getDueño().getEmail());
        ciudadanoTelefonoTF.setText(carroCompras.getDueño().getTelefono());

        // campos carroCompra
        carroIdTF.setText(carroCompras.getDueño().getId().toString());
        carroCodigoTF.setText(carroCompras.getCodigo());
        carroFechaTF.setText(carroCompras.getFecha().toString());
        carroHoraTF.setText(carroCompras.getHora().toString());
        carroImpuestoTF.setText(carroCompras.getImpuesto().toString());
        carroObservacionesTF.setText(carroCompras.getObservaciones());
        carroDetalleTF.setText(carroCompras.getDetalle());
        carroSubtotalTF.setText(carroCompras.getSubtotal().toString());
        carroTotalTF.setText(carroCompras.getTotal().toString());
    }

    @FXML
    void cancelar(ActionEvent event) throws IOException {
        App.setRoot("carro_compra");
    }

    @FXML
    void validarInformacion(ActionEvent event) throws Exception {
        carroComprasService.validar(this.carroCompras.getId());
        enviarMailValidacion();
        App.setRoot("carro_compra");
    }

    private void enviarMailValidacion() {
        Context context = new Context();

        Email email = new Email();
        email.setAsunto("CARRO DE COMPRA APROBADO");
        email.setDestinatario(carroCompras.getDueño().getEmail());
        email.setContenido(ThymeleafUtility.createEngine().process(
            "carro_compras_aprobado",
            context
        ));

        try {
            emailService.enviar(email);
        } catch (Exception e) {
            System.out.println("No se pudo enviar el email!!!!!!!!!!!!!!!!");
            // System.out.println(e);
            throw new RuntimeException(e);
        }
    }

    private void actualizarTablaDetalleProductos() {
        tablaDetalleProductos.setItems(FXCollections.observableArrayList(
            detalleProductoService.hallarPorCarroCompra(this.carroCompras.getId())
        ));
    }

    public void establecerCarroCompra(Integer id) {
        this.carroCompras = carroComprasService.hallarPorId(id).get();
    }
}
