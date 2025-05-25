package co.edu.uniquindio.aplicacion.producto;

import co.edu.uniquindio.aplicacion.exceptions.ValidacionException;
import co.edu.uniquindio.aplicacion.validador.producto.CrearProductoValidador;
import co.edu.uniquindio.dominio.producto.CrearProductoData;
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
     * @param datos informacion del producto
     * @return producto creado
     */
    public Producto execute(CrearProductoData datos) throws ValidacionException {
        Producto producto = new Producto();
        producto.setCodigo(datos.codigo());
        producto.setNombre(datos.nombre());
        producto.setPrecio(datos.precio());
        producto.setStock(datos.stock());
        producto.setDescripcion(datos.descripcion());
        producto.setCaracteristicas(datos.caracteristicas());
        producto.setCategoriaPrincipal(datos.categoriaPrincipal());
        producto.setCategoriaSecundaria(datos.categoriaSecundaria());
        producto.setEstado(datos.estado());

        new CrearProductoValidador(repositorio).validate(datos);

        return repositorio.guardar(producto);
    }
}
