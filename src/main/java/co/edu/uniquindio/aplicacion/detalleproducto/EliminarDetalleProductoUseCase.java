package co.edu.uniquindio.aplicacion.detalleproducto;

import co.edu.uniquindio.dominio.detalleproducto.DetalleProductoRepository;

/**
 * elimina el detalle de un producto
 */
public class EliminarDetalleProductoUseCase {
    private DetalleProductoRepository repositorio;

    public EliminarDetalleProductoUseCase(DetalleProductoRepository repositorio) {
        this.repositorio = repositorio;
    }

    /**
     * elimina el detalle de un producto
     * @param id id del detalle de un producto
     * @return {@code true} si el detalle fue eliminado o {@code false} si no es el caso
     */
    public Boolean execute(Integer id) {
        return repositorio.eliminar(id);
    }
}
