package co.edu.uniquindio;

import java.io.IOException;
import java.util.Map;
import java.util.function.Supplier;

import co.edu.uniquindio.factory.ServiceFactory;
import co.edu.uniquindio.presentacion.CarroComprasController;
import co.edu.uniquindio.presentacion.CiudadanoController;
import co.edu.uniquindio.presentacion.CrearCarroComprasController;
import co.edu.uniquindio.presentacion.CrearDetalleProductoController;
import co.edu.uniquindio.presentacion.DetalleProductoController;
import co.edu.uniquindio.presentacion.ProductoController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    private static Stage primaryStage;
    public static Scene scene;

    private static final Map<Class<?>, Supplier<?>> controllerProvider = Map.of(
        CiudadanoController.class,
        () -> new CiudadanoController(ServiceFactory.crearCiudadanoService()),
        ProductoController.class,
        () -> new ProductoController(ServiceFactory.crearProductoService()),
        CarroComprasController.class,
        () -> new CarroComprasController(ServiceFactory.crearCarroComprasService()),
        DetalleProductoController.class,
        () -> new DetalleProductoController(
            ServiceFactory.crearDetalleProductoService(),
            ServiceFactory.crearProductoService(),
            ServiceFactory.crearCarroComprasService()
        ),
        CrearCarroComprasController.class,
        () -> new CrearCarroComprasController(
            ServiceFactory.crearCarroComprasService(),
            ServiceFactory.crearCiudadanoService(),
            ServiceFactory.crearDetalleProductoService()
        ),
        CrearDetalleProductoController.class,
        () -> new CrearDetalleProductoController(ServiceFactory.crearProductoService())
    );

    private static Object controllerFactory(Class<?> type) {
        return controllerProvider.getOrDefault(
            type,
            () -> {
                try {
                    return type.getDeclaredConstructor().newInstance();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        ).get();
    }

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        scene = new Scene(loadFXML("main"));
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
        primaryStage.sizeToScene();
        primaryStage.centerOnScreen();
    }

    public static void setRoot(Parent parent) throws Exception {
        scene.setRoot(parent);
        primaryStage.sizeToScene();
        primaryStage.centerOnScreen();
    }

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader(
            App.class.getClassLoader().getResource("views/" + fxml + ".fxml")
        );

        loader.setControllerFactory(App::controllerFactory);

        return loader.load();
    }

    @SuppressWarnings("unchecked")
    public static <T> T getControllerFromFactory(Class<T> controllerClass) {
        return (T) controllerProvider.get(controllerClass).get();
    }

    public static void main(String[] args) {
        launch(args);
    }
}