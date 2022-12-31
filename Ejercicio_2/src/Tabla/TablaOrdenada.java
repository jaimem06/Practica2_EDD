package Tabla;

import Exceptions.PosicionException;
import TDA_Lista.ListaEnlazada;
import Modelo.Datos_Comida;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Jaime Mendoza
 */
public class TablaOrdenada extends AbstractTableModel {

    ListaEnlazada<Datos_Comida> listaComida = new ListaEnlazada<>();

    public ListaEnlazada<Datos_Comida> getListaComida() {
        return listaComida;
    }

    public void setListaComida(ListaEnlazada<Datos_Comida> listaComida) {
        this.listaComida = listaComida;
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public int getRowCount() {
        return listaComida.getSize();
    }

    @Override
    public String getColumnName(int column) {
        switch (column)
        {
            case 0:
                return "Restaurante";
            case 1:
                return "Sabor";
            case 2:
                return "Tamaño";
            case 3:
                return "Precio";
            default:
                return null;
        }
    }

    @Override
    public Object getValueAt(int arg0, int arg1) {
        Datos_Comida comida;
        try
        {
            comida = listaComida.obtenerDato(arg0);
            switch (arg1)
            {
                case 0:
                    return comida.getRestaurante();
                case 1:
                    return comida.getSabor();
                case 2:
                    return comida.getTamanio();
                case 3:
                    return comida.getPrecio();
                default:
                    return null;
            }
        } catch (PosicionException ex)
        {
            Logger.getLogger(TablaOrdenada.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
