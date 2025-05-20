package co.edu.uniquindio.dominio.carro;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import co.edu.uniquindio.dominio.ciudadano.Ciudadano;
import co.edu.uniquindio.dominio.detalleproducto.DetalleProducto;

public class CarroCompras {
    private Integer id;

    private String codigo;

    private LocalDate fecha;

    private LocalTime hora;

    private CarroCompraEstado estado;

    private String observaciones;

    private String detalle;

    private Float impuesto;

    private Integer subtotal;

    private Integer total;

    private Ciudadano dueño;

    private Map<Integer, DetalleProducto> detalleProductos;

    public CarroCompras() {
        this.detalleProductos = new HashMap<>();
    }

    public CarroCompras(
        Integer id,
        String codigo,
        LocalDate fecha,
        LocalTime hora,
        CarroCompraEstado estado,
        String observaciones,
        String detalle,
        Float impuesto,
        Integer subtotal,
        Integer total
    ) {
        this();
        this.id = id;
        this.codigo = codigo;
        this.fecha = fecha;
        this.hora = hora;
        this.estado = estado;
        this.observaciones = observaciones;
        this.detalle = detalle;
        this.impuesto = impuesto;
        this.subtotal = subtotal;
        this.total = total;
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

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public CarroCompraEstado getEstado() {
        return estado;
    }

    public void setEstado(CarroCompraEstado estado) {
        this.estado = estado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public Float getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(Float impuesto) {
        this.impuesto = impuesto;
    }

    public Integer getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Integer subtotal) {
        this.subtotal = subtotal;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Ciudadano getDueño() {
        return dueño;
    }

    public void setDueño(Ciudadano dueño) {
        this.dueño = dueño;
    }

    public Map<Integer, DetalleProducto> getDetalleProductos() {
        return detalleProductos;
    }

    public void setDetalleProductos(Map<Integer, DetalleProducto> detalleProductos) {
        this.detalleProductos = detalleProductos;
    }

    public Float calcularSubtotal() {
        Double suma = detalleProductos.values().stream()
            .mapToDouble(dp -> dp.calcularSubtotal())
            .sum()
        ;

        return suma.floatValue();
    }

    public Integer calcularTotal() {
        Integer suma = detalleProductos.values().stream()
            .mapToInt(dp -> dp.calcularTotal())
            .sum()
        ;

        return suma;
    }
}
