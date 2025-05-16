package co.edu.uniquindio.aplicacion.producto;

import java.util.Optional;

import co.edu.uniquindio.dominio.producto.Producto;
import co.edu.uniquindio.dominio.producto.ProductoRepository;

public class ProductoService {
    // private ProductoRepository repositorio;

    private CrearProductoUseCase crearProducto;
    private HallarProductoUseCase hallarProducto;

    public ProductoService(ProductoRepository repositorio) {
        // this.repositorio = repositorio;
        crearProducto = new CrearProductoUseCase(repositorio);
        hallarProducto = new HallarProductoUseCase(repositorio);
    }

    public Producto crear(Producto producto) {
        return crearProducto.execute(producto);
    }

    public Optional<Producto> hallarPorId(Integer id) {
        return hallarProducto.execute(id);
    }
}
