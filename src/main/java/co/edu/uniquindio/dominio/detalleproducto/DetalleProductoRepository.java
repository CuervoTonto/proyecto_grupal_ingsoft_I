package co.edu.uniquindio.dominio.detalleproducto;

import java.util.Optional;

public interface DetalleProductoRepository {
    public DetalleProducto guardar(DetalleProducto detalleProducto);
    public Optional<DetalleProducto> hallar(Integer id);
    public Boolean eliminar(Integer id);
}
