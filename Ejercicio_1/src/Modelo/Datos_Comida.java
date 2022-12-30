package Modelo;

/**
 * @author Jaime Mendoza
 */
public class Datos_Comida {

    private String sabor;
    private Double precio;
    private String restaurante;
    private String tamanio;

    public String getSabor() {
        return sabor;
    }

    public void setSabor(String sabor) {
        this.sabor = sabor;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(String restaurante) {
        this.restaurante = restaurante;
    }

    public String getTamanio() {
        return tamanio;
    }

    public void setTamanio(String tamanio) {
        this.tamanio = tamanio;
    }

    public Datos_Comida(String sabor, Double precio, String restaurante, String tamanio) {
        this.sabor = sabor;
        this.precio = precio;
        this.restaurante = restaurante;
        this.tamanio = tamanio;
    }

    public Datos_Comida() {
    }

    @Override
    public String toString() {
        return "Datos_Comida{" + "sabor=" + sabor + ", precio=" + precio + ", restaurante=" + restaurante + ", tamanio=" + tamanio + '}';
    }

}
