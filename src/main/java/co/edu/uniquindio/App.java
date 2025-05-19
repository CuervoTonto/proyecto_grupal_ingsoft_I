package co.edu.uniquindio;

import java.io.IOException;
import java.util.Map;
import java.util.function.Supplier;

import co.edu.uniquindio.factory.ServiceFactory;
import co.edu.uniquindio.presentacion.CarroComprasController;
import co.edu.uniquindio.presentacion.CiudadanoController;
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
        () -> new CarroComprasController(ServiceFactory.crearCarroComprasService(), ServiceFactory.crearCiudadanoService()),
        DetalleProductoController.class,
        () -> new DetalleProductoController(
            ServiceFactory.crearDetalleProductoService(),
            ServiceFactory.crearProductoService(),
            ServiceFactory.crearCarroComprasService()
        )
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
    }

    public static void setRoot(Parent parent) throws Exception {
        scene.setRoot(parent);
        primaryStage.sizeToScene();
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader(
            App.class.getClassLoader().getResource("views/" + fxml + ".fxml")
        );

        loader.setControllerFactory(App::controllerFactory);

        return loader.load();
    }

    public static void main(String[] args) {
        launch(args);
    }
}