package co.edu.uniquindio.dominio.producto;

import java.util.Optional;

public interface ProductoRepository {
    public Producto guardar(Producto producto);
    public Optional<Producto> hallar(Integer id);
}
