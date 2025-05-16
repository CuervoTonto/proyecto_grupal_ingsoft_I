package co.edu.uniquindio.dominio.detalleproducto;

import java.time.LocalDate;

import co.edu.uniquindio.dominio.carro.CarroCompras;
import co.edu.uniquindio.dominio.producto.Producto;

public class DetalleProducto {
    private Integer id;

    private DetalleProductoEstado estado;

    private Integer cantidad;

    private String concepto;

    private Integer monto;

    private Float impuesto;

    private Float subtotal;

    private LocalDate fechaEntrega;

    private LocalDate fechaPrestamo;
    
    private CarroCompras carroCompras;

    private Producto producto;

    public DetalleProducto() {}

    public DetalleProducto(
        Integer id,
        DetalleProductoEstado estado,
        Integer cantidad,
        String concepto,
        Integer monto,
        Float impuesto,
        Float subtotal,
        LocalDate fechaEntrega,
        LocalDate fechaPrestamo
    ) {
        this.id = id;
        this.estado = estado;
        this.cantidad = cantidad;
        this.concepto = concepto;
        this.monto = monto;
        this.impuesto = impuesto;
        this.subtotal = subtotal;
        this.fechaEntrega = fechaEntrega;
        this.fechaPrestamo = fechaPrestamo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public DetalleProductoEstado getEstado() {
        return estado;
    }

    public void setEstado(DetalleProductoEstado estado) {
        this.estado = estado;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public Integer getMonto() {
        return monto;
    }

    public void setMonto(Integer monto) {
        this.monto = monto;
    }

    public Float getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(Float impuesto) {
        this.impuesto = impuesto;
    }

    public Float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Float subtotal) {
        this.subtotal = subtotal;
    }

    public LocalDate getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(LocalDate fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(LocalDate fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public CarroCompras getCarroCompras() {
        return carroCompras;
    }

    public void setCarroCompras(CarroCompras carroCompras) {
        this.carroCompras = carroCompras;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}
