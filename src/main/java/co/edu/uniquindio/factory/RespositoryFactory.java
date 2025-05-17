package co.edu.uniquindio.factory;

import co.edu.uniquindio.dominio.carro.CarroComprasRepository;
import co.edu.uniquindio.dominio.ciudadano.CiudadanoRepository;
import co.edu.uniquindio.dominio.detalleproducto.DetalleProductoRepository;
import co.edu.uniquindio.dominio.producto.ProductoRepository;
import co.edu.uniquindio.infraestructura.carro.memory.InMemoryCarritoComprasRepository;
import co.edu.uniquindio.infraestructura.ciudadano.memory.InMemoryCiudadanoRepository;
import co.edu.uniquindio.infraestructura.detalleproducto.memory.InMemoryDetalleProductoRespository;
import co.edu.uniquindio.infraestructura.producto.memory.InMemoryProductoRepository;

public class RespositoryFactory {
    private RespositoryFactory() {}

    public static CiudadanoRepository crearCiudadanoRepository() {
        return InMemoryCiudadanoRepository.getInstancia();
    }

    public static ProductoRepository crearProductoRepository() {
        return InMemoryProductoRepository.getInstancia();
    }

    public static CarroComprasRepository crearCarroComprasRepository() {
        return InMemoryCarritoComprasRepository.getInstancia();
    }

    public static DetalleProductoRepository creaDetalleProductoRepository() {
        return InMemoryDetalleProductoRespository.getInstancia();
    }
}
