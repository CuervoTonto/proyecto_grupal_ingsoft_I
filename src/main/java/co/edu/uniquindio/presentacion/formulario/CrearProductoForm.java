package co.edu.uniquindio.presentacion.formulario;

import co.edu.uniquindio.dominio.producto.CrearProductoData;

public record CrearProductoForm(
    String codigo,
    String nombre,
    String precio,
    String stock,
    String descripcion,
    String categoriaPrincipal,
    String categoriaSecundaria,
    String caracteristicas
) {
    public CrearProductoData toData() {
        return new CrearProductoData(
            codigo,
            nombre,
            categoriaPrincipal,
            categoriaSecundaria,
            descripcion,
            Float.parseFloat(precio),
            Integer.parseInt(stock),
            caracteristicas
        );
    }
}
