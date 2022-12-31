package Tabla;

import TDA_Lista.ListaEnlazadaServices;
import Modelo.Datos_Comida;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Jaime Mendoza
 */
public class TablaD_Aleatorios extends AbstractTableModel {

    ListaEnlazadaServices<Datos_Comida> listaComida = new ListaEnlazadaServices<>();

    public ListaEnlazadaServices<Datos_Comida> getListaComida() {
        return listaComida;
    }

    public void setListaComida(ListaEnlazadaServices<Datos_Comida> listaComida) {
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
        Datos_Comida comida = listaComida.obtenerDato(arg0);
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
    }
}
