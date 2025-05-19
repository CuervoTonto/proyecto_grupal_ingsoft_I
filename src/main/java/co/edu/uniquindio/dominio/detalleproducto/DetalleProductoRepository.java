package co.edu.uniquindio.dominio.detalleproducto;

import java.util.List;
import java.util.Optional;

public interface DetalleProductoRepository {
    public DetalleProducto guardar(DetalleProducto detalleProducto);
    public Optional<DetalleProducto> hallar(Integer id);
    public List<DetalleProducto> hallarTodos();
    public List<DetalleProducto> obtenerPorCarroCompras(Integer carroComprasId);
    public Boolean eliminar(Integer id);
}
