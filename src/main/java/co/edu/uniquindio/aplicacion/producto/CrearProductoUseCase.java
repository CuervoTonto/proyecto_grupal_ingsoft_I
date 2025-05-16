package co.edu.uniquindio.aplicacion.producto;

import co.edu.uniquindio.dominio.producto.Producto;
import co.edu.uniquindio.dominio.producto.ProductoRepository;

/**
 * crea un producto
 */
public class CrearProductoUseCase {
    private ProductoRepository repositorio;

    public CrearProductoUseCase(ProductoRepository repositorio) {
        this.repositorio = repositorio;
    }

    /**
     * crea un producto
     * @param producto informacion del producto
     * @return producto creado
     */
    public Producto execute(Producto producto) {
        return repositorio.guardar(producto);
    }
}
