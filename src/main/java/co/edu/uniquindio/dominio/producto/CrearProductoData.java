package co.edu.uniquindio.dominio.producto;

public record CrearProductoData(
    String codigo,
    String nombre,
    String categoriaPrincipal,
    String categoriaSecundaria,
    String descripcion,
    Float precio,
    Integer stock,
    String caracteristicas
) {
    public ProductoEstado estado() {
        return ProductoEstado.ACTIVO;
    }
}