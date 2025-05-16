package co.edu.uniquindio.dominio.ciudadano;

import java.util.Map;

import co.edu.uniquindio.dominio.carro.CarroCompras;

public class Ciudadano {
    private Integer id;
    
    private String cedula;

    private String nombre;

    private String apellido;
    
    private String telefono;

    private String email;

    private CiudadanoEstado estado;

    private Map<Integer, CarroCompras> carroCompras;

    public Ciudadano() {}

    public Ciudadano(Integer id,
        String cedula,
        String nombre,
        String apellido,
        String telefono,
        String email,
        CiudadanoEstado estado
    ) {
        this.id = id;
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.email = email;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CiudadanoEstado getEstado() {
        return estado;
    }

    public void setEstado(CiudadanoEstado estado) {
        this.estado = estado;
    }

    public Map<Integer, CarroCompras> getCarroCompras() {
        return carroCompras;
    }

    public void setCarroCompras(Map<Integer, CarroCompras> carroCompras) {
        this.carroCompras = carroCompras;
    }
}
