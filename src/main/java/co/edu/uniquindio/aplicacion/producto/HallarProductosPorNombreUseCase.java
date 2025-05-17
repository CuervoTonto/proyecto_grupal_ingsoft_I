package co.edu.uniquindio.aplicacion.producto;

import java.util.List;

import co.edu.uniquindio.dominio.producto.Producto;
import co.edu.uniquindio.dominio.producto.ProductoRepository;

/**
 * obtiene los productos basados en el nombre
 */
public class HallarProductosPorNombreUseCase {
    private ProductoRepository repositorio;

    public HallarProductosPorNombreUseCase(ProductoRepository repositorio) {
        this.repositorio = repositorio;
    }

    /**
     * obtiene los productos basados en su nombre
     * @param nombre nombre o coincidencia para el nombre del producto
     * @return productos coincidentes
     */
    public List<Producto> execute(String nombre) {
        return repositorio.hallarProductosPorNombre(nombre);
    }
}
