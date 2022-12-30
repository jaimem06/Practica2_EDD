package Controlador;

import TDA_Lista.ListaEnlazadaServices;
import TDA_Lista.TipoOrdenacion;
import Modelo.Datos_Comida;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Jaime Mendoza
 */
public class Controlador_Comida {

    Datos_Comida comidaPrueba = new Datos_Comida();
    ListaEnlazadaServices<Datos_Comida> listaComida = new ListaEnlazadaServices<>();
    Datos_Comida[] comidaArreglo = new Datos_Comida[20000];

    public Datos_Comida[] getComidaArreglo() {
        return comidaArreglo;
    }

    public void setComidaArreglo(Datos_Comida[] comidaArreglo) {
        this.comidaArreglo = comidaArreglo;

    }

    public Datos_Comida getComidaPrueba() {
        return comidaPrueba;
    }

    public void setComidaPrueba(Datos_Comida comidaPrueba) {
        this.comidaPrueba = comidaPrueba;
    }

    public ListaEnlazadaServices<Datos_Comida> getListaComida() {
        return listaComida;
    }

    public void setListaComida(ListaEnlazadaServices<Datos_Comida> listaComida) {
        this.listaComida = listaComida;
    }

    public Controlador_Comida() {
    }

    public ListaEnlazadaServices<Datos_Comida> guardarLista() {
        Datos_Comida cuenta;
        for (int i = 0; i < 20000; i++)
        {
            cuenta = (new Datos_Comida(sabor()[i], precio(), restaurante()[i], tamanio()[i]));
            listaComida.insertarAlInicio(cuenta);
            setListaComida(listaComida);
        }
        return getListaComida();
    }

    public String[] restaurante() {

        String[] restaurantesAleatorios = new String[20000];

        String[] restaurantes =
        {
            "Domino's", "Pizza House", "Papa John's", "Pizza Hut", "Roma Pizzeria", "Forno di Fango", "Italian Pizza"
        };
        for (int i = 0; i < 20000; i++)
        {
            restaurantesAleatorios[i] = restaurantes[(int) (Math.floor(Math.random() * ((restaurantes.length - 1) - 0 + 1)))];
        }
        return restaurantesAleatorios;
    }

    public String[] sabor() {
        String[] saboresAleatorios = new String[20000];

        String[] sabores =
        {
            "Pizza 4 Estaciones", "Pizza Marinara", "Pizza Hawaina", "Pizza Pepperoni", "Pizza Napolitana", "Pizza 4 Quesos", "Pizza Vegetariana", "Pizza Campestre", "Pizza Pomodoro", "Pizza Chicken BBK", "Pizza Italiana",
            "Pizza New York", "Pizza Personalizada", "Pizza Champiñones", "Pizza Tradicional", "Pizza Margherita", "Pizza Deluxe", "Pizza Cheseburger", "Pizza Carnitzza", "Pizza Caprese", "Pizza Verde", "Pizza Carbonara"
        };
        for (int i = 0; i < 20000; i++)
        {
            saboresAleatorios[i] = sabores[(int) (Math.floor(Math.random() * ((sabores.length - 1) - 0 + 1)))];
        }
        return saboresAleatorios;
    }

    public String[] tamanio() {
        String[] tamaniosAleatorios = new String[20000];

        String[] tamanios =
        {
            "Familiar", "Mediana", "Pequeña"
        };
        for (int i = 0; i < 20000; i++)
        {
            tamaniosAleatorios[i] = tamanios[(int) (Math.floor(Math.random() * ((tamanios.length - 1) - 0 + 1)))];
        }
        return tamaniosAleatorios;
    }

    public double precio() {
        double minValor = 8.0;
        double maxValor = 20.0;
        double precio = (Math.random() * (maxValor - minValor) + 10);
        return Math.round(precio * 100.0) / 100.0;
    }

    public void ordenarShell(String atributo, TipoOrdenacion ordenacion) {
        try
        {
            getListaComida().getLista().ordenarShell(atributo, ordenacion);
        } catch (Exception ex)
        {
            Logger.getLogger(Controlador_Comida.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ordenarQuickShort(String atributo, TipoOrdenacion ordenacion) {
        try
        {
            getListaComida().getLista().ordenarQuickShort(atributo, ordenacion);
        } catch (Exception ex)
        {
            Logger.getLogger(Controlador_Comida.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
