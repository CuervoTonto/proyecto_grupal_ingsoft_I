package co.edu.uniquindio.factory;

import co.edu.uniquindio.aplicacion.carro.CarroComprasService;
import co.edu.uniquindio.aplicacion.ciudadano.CiudadanoService;
import co.edu.uniquindio.aplicacion.detalleproducto.DetalleProductoService;
import co.edu.uniquindio.aplicacion.producto.ProductoService;

public class ServiceFactory {
    private ServiceFactory() {}

    public static CiudadanoService crearCiudadanoService() {
        return new CiudadanoService(RespositoryFactory.crearCiudadanoRepository());
    }

    public static ProductoService crearProductoService() {
        return new ProductoService(RespositoryFactory.crearProductoRepository());
    }

    public static CarroComprasService crearCarroComprasService() {
        return new CarroComprasService(RespositoryFactory.crearCarroComprasRepository());
    }

    public static DetalleProductoService crearDetalleProductoService() {
        return new DetalleProductoService(RespositoryFactory.creaDetalleProductoRepository());
    }
}
