package co.edu.uniquindio.aplicacion.producto;

import java.util.List;
import java.util.Optional;

import co.edu.uniquindio.dominio.producto.Producto;
import co.edu.uniquindio.dominio.producto.ProductoRepository;

public class ProductoService {
    // private ProductoRepository repositorio;

    private CrearProductoUseCase crearProducto;
    private HallarProductoUseCase hallarProducto;
    private HallarProductosPorNombreUseCase hallarProductosPorNombre;

    public ProductoService(ProductoRepository repositorio) {
        // this.repositorio = repositorio;
        crearProducto = new CrearProductoUseCase(repositorio);
        hallarProducto = new HallarProductoUseCase(repositorio);
        hallarProductosPorNombre = new HallarProductosPorNombreUseCase(repositorio);
    }

    public Producto crear(Producto producto) {
        return crearProducto.execute(producto);
    }

    public Optional<Producto> hallarPorId(Integer id) {
        return hallarProducto.execute(id);
    }

    public List<Producto> hallarPorNombre(String nombre) {
        return hallarProductosPorNombre.execute(nombre);
    }
}
