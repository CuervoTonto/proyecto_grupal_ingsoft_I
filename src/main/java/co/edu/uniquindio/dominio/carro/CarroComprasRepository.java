package co.edu.uniquindio.dominio.carro;

import java.util.List;
import java.util.Optional;

public interface CarroComprasRepository {
    /**
     * guarda (crea o actualiza) un carro de compras
     * @param carro carrito a guardar
     * @return el carrito almacenado
     * 
     * si el carrito tiene establecido un id se actualiza o 
     * si no lo tiene se crea y almacena
     */
    public CarroCompras guardar(CarroCompras carro);

    /**
     * elimina un carro de compras
     * @param id id del carro de compras
     * @return {@code true} si se elimino o {@code false} en el caso contrario 
     */
    public Boolean eliminar(Integer id);

    /**
     * busca la informacion de un carrio de comprasa almacenado
     * @param id id del carro de compras
     * @return carrito de compras si se encuentra
     */
    public Optional<CarroCompras> hallar(Integer id);

    /**
     * busca la informacion de los carros de compra basados en el id del ciudadano
     * @param ciudadanoId id del cliente (dueño de los carritos)
     * @returns carros de compra del cliente o {@code null} de otra forma
     */
    public List<CarroCompras> hallarCarrosComprasPorCliente(Integer ciudadanoId);

    /**
     * halla todos las carros de compra
     * 
     * @return carros de compra en el repositorio
     */
    public List<CarroCompras> hallarTodos();
}
