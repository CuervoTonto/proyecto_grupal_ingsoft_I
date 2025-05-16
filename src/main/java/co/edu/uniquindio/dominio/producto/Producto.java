package co.edu.uniquindio.dominio.producto;

import java.util.Map;

import co.edu.uniquindio.dominio.detalleproducto.DetalleProducto;

public class Producto {
    private Integer id;
    
    private String codigo;

    private String nombre;

    private String categoriaPrincipal;

    private String categoriaSecundaria;

    private String descripcion;

    private Float precio;

    private Integer stock;

    private String caracteristicas;

    private ProductoEstado estado;

    private Map<Integer, DetalleProducto> detallesProductos;

    public Producto() {}

    public Producto(
        Integer id,
        String codigo,
        String nombre,
        String categoriaPrincipal,
        String categoriaSecundaria,
        String descripcion,
        Float precio,
        Integer stock,
        String caracteristicas,
        ProductoEstado estado
    ) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.categoriaPrincipal = categoriaPrincipal;
        this.categoriaSecundaria = categoriaSecundaria;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.caracteristicas = caracteristicas;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoriaPrincipal() {
        return categoriaPrincipal;
    }

    public void setCategoriaPrincipal(String categoriaPrincipal) {
        this.categoriaPrincipal = categoriaPrincipal;
    }

    public String getCategoriaSecundaria() {
        return categoriaSecundaria;
    }

    public void setCategoriaSecundaria(String categoriaSecundaria) {
        this.categoriaSecundaria = categoriaSecundaria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public ProductoEstado getEstado() {
        return estado;
    }

    public void setEstado(ProductoEstado estado) {
        this.estado = estado;
    }

    public Map<Integer, DetalleProducto> getDetallesProductos() {
        return detallesProductos;
    }

    public void setDetallesProductos(Map<Integer, DetalleProducto> detallesProductos) {
        this.detallesProductos = detallesProductos;
    }
}
