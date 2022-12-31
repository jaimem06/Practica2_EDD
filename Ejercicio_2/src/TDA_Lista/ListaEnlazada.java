package TDA_Lista;

import Exceptions.PosicionException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import static TDA_Lista.Utilidades.getMethod;

/**
 *
 * @author Jaime Mendoza
 */
public class ListaEnlazada<E> {

    private NodoLista<E> cabecera;

    private Integer size;

    public NodoLista<E> getCabecera() {
        return cabecera;
    }

    public void setCabecera(NodoLista<E> cabecera) {
        this.cabecera = cabecera;
    }

    /**
     * Constructor de la clase se inicializa la lista en null y el tamanio en 0
     */
    public ListaEnlazada() {
        cabecera = null;
        size = 0;
    }

    /**
     * Permite ver si la lista esta vacia
     *
     * @return Boolean true si esta vacia, false si esta llena
     */
    public Boolean estaVacia() {
        return cabecera == null;
    }

    private void insertar(E dato) {
        NodoLista<E> nuevo = new NodoLista<>(dato, null);
        if (estaVacia())
        {
            cabecera = nuevo;
        } else
        {
            NodoLista<E> aux = cabecera;
            while (aux.getSiguiente() != null)
            {
                aux = aux.getSiguiente();
            }
            aux.setSiguiente(nuevo);
        }
        size++;
    }

    public void insertarCabecera(E dato) {
        if (estaVacia())
        {
            insertar(dato);
        } else
        {
            NodoLista<E> nuevo = new NodoLista<>(dato, null);

            nuevo.setSiguiente(cabecera);
            cabecera = nuevo;
            size++;
        }
    }

    public void insertar(E dato, Integer pos) throws PosicionException {
        //lista size = 1
        if (estaVacia())
        {
            insertar(dato);
        } else if (pos >= 0 && pos < size)
        {
            NodoLista<E> nuevo = new NodoLista<>(dato, null);
            if (pos == (size - 1))
            {
                insertar(dato);

            } else
            {

                NodoLista<E> aux = cabecera;
                for (int i = 0; i < pos - 1; i++)
                {
                    aux = aux.getSiguiente();
                }
                NodoLista<E> siguiente = aux.getSiguiente();
                aux.setSiguiente(nuevo);
                nuevo.setSiguiente(siguiente);
                size++;
            }

        } else
        {
            throw new PosicionException("Error en insertar: No existe la posicion dada");
        }
    }

    public void imprimir() {
        NodoLista<E> aux = cabecera;
        for (int i = 0; i < getSize(); i++)
        {

            System.out.println(aux.getDato().toString() + "\t");
            aux = aux.getSiguiente();
        }
    }

    public Integer getSize() {
        return size;
    }

    /**
     * Metodo que permite obtener un dato segun la posicion
     *
     * @param pos posicion en la lista
     * @return Elemento
     */
    public E obtenerDato(Integer pos) throws PosicionException {
        if (!estaVacia())
        {
            if (pos >= 0 && pos < size)
            {
                E dato = null;
                if (pos == 0)
                {
                    dato = cabecera.getDato();
                } else
                {
                    NodoLista<E> aux = cabecera;
                    for (int i = 0; i < pos; i++)
                    {
                        aux = aux.getSiguiente();
                    }
                    dato = aux.getDato();
                }

                return dato;
            } else
            {
                throw new PosicionException("Error en obtener dato: No existe la posicion dada");
            }

        } else
        {
            throw new PosicionException("Error en obtener dato: La lista esta vacia, por endde no hay esa posicion");
        }
    }

    public E eliminarDato(Integer pos) throws PosicionException {
        E auxDato = null;
        if (!estaVacia())
        {
            if (pos >= 0 && pos < size)
            {
                if (pos == 0)
                {
                    auxDato = cabecera.getDato();
                    cabecera = cabecera.getSiguiente();
                    size--;
                } else
                {
                    NodoLista<E> aux = cabecera;
                    for (int i = 0; i < pos - 1; i++)
                    {
                        aux = aux.getSiguiente();
                    }
                    auxDato = aux.getDato();
                    NodoLista<E> proximo = aux.getSiguiente();
                    aux.setSiguiente(proximo.getSiguiente());
                    size--;
                }
                return auxDato;

            } else
            {
                throw new PosicionException("Error en eliminar dato: No existe la posicion dada");
            }

        } else
        {
            throw new PosicionException("Error en eliminar dato: La lista esta vacia, por endde no hay esa posicion");
        }
    }

    public void vaciar() {
        cabecera = null;
        size = 0;
    }

    public void modificarDato(Integer pos, E datoM) throws PosicionException {
        if (!estaVacia())
        {
            if (pos >= 0 && pos < size)
            {
                if (pos == 0)
                {
                    cabecera.setDato(datoM);
                } else
                {
                    NodoLista<E> aux = cabecera;
                    for (int i = 0; i < pos; i++)
                    {
                        aux = aux.getSiguiente();
                    }
                    aux.setDato(datoM);
                }

            } else
            {
                throw new PosicionException("Error en obtener dato: No existe la posicion dada");
            }

        } else
        {
            throw new PosicionException("Error en obtener dato: La lista esta vacia, por endde no hay esa posicion");
        }
    }

    public E[] toArray() {
        Class<E> clazz = null;
        E[] matriz = null;
        if (this.size > 0)
        {
            matriz = (E[]) java.lang.reflect.Array.newInstance(cabecera.getDato().getClass(), this.size);
            NodoLista<E> aux = cabecera;
            for (int i = 0; i < this.size; i++)
            {
                matriz[i] = aux.getDato();
                aux = aux.getSiguiente();
            }
        }

        return matriz;
    }

    public ListaEnlazada<E> toList(E[] matriz) {
        this.vaciar();
        for (int i = 0; i < matriz.length; i++)
        {
            this.insertar(matriz[i]);
        }
        return this;
    }

    public ListaEnlazada<E> ordenarShell(String atributo, TipoOrdenacion tipoOrdenacion) throws PosicionException, Exception {
        Class<E> clazz = null;
        E[] matriz = null;
        if (size > 0)
        {
            clazz = (Class<E>) cabecera.getDato().getClass();
            Boolean isObject = Utilidades.isObject(clazz);
            matriz = toArray();
            int i, salto;
            boolean ordenado;

            for (salto = matriz.length / 2; salto != 0; salto /= 2)
            {
                ordenado = true;
                while (ordenado)
                {
                    ordenado = false;
                    for (i = salto; i < matriz.length; i++)
                    {
                        if (isObject)
                        {
                            Field field = Utilidades.getField(atributo, clazz);
                            Method method = getMethod("get" + Utilidades.capitalizar(atributo), matriz[i - salto].getClass());
                            Method method1 = getMethod("get" + Utilidades.capitalizar(atributo), matriz[i].getClass());
                            Object[] aux = evaluaCambiarDato(field.getType(), matriz[i - salto], matriz[i], method, method1, tipoOrdenacion, i - salto, matriz);
                            if (aux[0] != null)
                            {
                                E cambio = matriz[i];
                                matriz[i] = matriz[i - salto];
                                matriz[i - salto] = cambio;
                                ordenado = true;
                            }
                        } else
                        {
                            Object[] aux = evaluaCambiarDatoNoObjeto(clazz, matriz[i - salto], matriz[i], tipoOrdenacion, i - salto, matriz);
                            if (aux[0] != null)
                            {
                                E cambio = matriz[i];
                                matriz[i] = matriz[i - salto];
                                matriz[i - salto] = cambio;
                                ordenado = true;
                            }
                        }
                    }
                }
            }
        }
        if (matriz != null)
        {
            toList(matriz);
        }
        return this;
    }

    public ListaEnlazada<E> ordenarQuickShort(String atributo, TipoOrdenacion tipoOrdenacion) throws PosicionException, Exception {
        Class<E> clazz = null;
        E[] matriz = null;
        if (size > 0)
        {
            clazz = (Class<E>) cabecera.getDato().getClass();
            Field field = Utilidades.getField(atributo, clazz);
            matriz = toArray();
            realizarQuick(field.getType(), matriz, 0, matriz.length - 1, atributo, tipoOrdenacion);
        }
        if (matriz != null)
        {
            toList(matriz);
        }
        return this;
    }

    private void realizarQuick(Class clazz, E[] matriz, int izq, int der, String atributo, TipoOrdenacion tipoOrdenacion) throws Exception {
        E pivote = matriz[izq];
        int i = izq;
        int j = der;

        while (i < j)
        {
            Method method = getMethod("get" + Utilidades.capitalizar(atributo), matriz[i].getClass());
            Method method1 = getMethod("get" + Utilidades.capitalizar(atributo), matriz[j].getClass());
            Method method2 = getMethod("get" + Utilidades.capitalizar(atributo), pivote.getClass());
            if (Utilidades.isNumber(clazz))
            {
                if (tipoOrdenacion.toString().equalsIgnoreCase(TipoOrdenacion.ASCENDENTE.toString()))
                {
                    while (((Number) method.invoke(matriz[i])).doubleValue() <= ((Number) method2.invoke(pivote)).doubleValue() && i < j)
                    {
                        i++;
                    }
                    while (((Number) method1.invoke(matriz[j])).doubleValue() > ((Number) method2.invoke(pivote)).doubleValue())
                    {
                        j--;
                    }
                } else
                {
                    while (((Number) method.invoke(matriz[i])).doubleValue() >= ((Number) method2.invoke(pivote)).doubleValue() && i < j)
                    {
                        i++;
                    }
                    while (((Number) method1.invoke(matriz[j])).doubleValue() < ((Number) method2.invoke(pivote)).doubleValue())
                    {
                        j--;
                    }
                }
                if (i < j)
                {
                    E aux = matriz[i];
                    matriz[i] = matriz[j];
                    matriz[j] = aux;
                }

            } else if (Utilidades.isString(clazz))
            {
                if (tipoOrdenacion.toString().equalsIgnoreCase(TipoOrdenacion.ASCENDENTE.toString()))
                {
                    while (((String) method.invoke(matriz[i])).toLowerCase().compareTo(((String) method2.invoke(pivote)).toLowerCase()) <= 0 && i < j)
                    {
                        i++;
                    }
                    while (((String) method1.invoke(matriz[j])).toLowerCase().compareTo(((String) method2.invoke(pivote)).toLowerCase()) > 0)
                    {
                        j--;
                    }
                } else
                {
                    while (((String) method.invoke(matriz[i])).toLowerCase().compareTo(((String) method2.invoke(pivote)).toLowerCase()) >= 0 && i < j)
                    {
                        i++;
                    }
                    while (((String) method1.invoke(matriz[j])).toLowerCase().compareTo(((String) method2.invoke(pivote)).toLowerCase()) < 0)
                    {
                        j--;
                    }
                }
                if (i < j)
                {
                    E aux = matriz[i];
                    matriz[i] = matriz[j];
                    matriz[j] = aux;
                }

            } else if (Utilidades.isCharacter(clazz))
            {
                if (tipoOrdenacion.toString().equalsIgnoreCase(TipoOrdenacion.ASCENDENTE.toString()))
                {
                    while (((Character) method.invoke(matriz[i])) <= ((Character) method2.invoke(pivote)) && i < j)
                    {
                        i++;
                    }
                    while (((Character) method1.invoke(matriz[j])) > ((Character) method2.invoke(pivote)))
                    {
                        j--;
                    }
                } else
                {
                    while (((Character) method.invoke(matriz[i])) >= ((Character) method2.invoke(pivote)) && i < j)
                    {
                        i++;
                    }
                    while (((Character) method1.invoke(matriz[j])) < ((Character) method2.invoke(pivote)))
                    {
                        j--;
                    }
                }
                if (i < j)
                {
                    E aux = matriz[i];
                    matriz[i] = matriz[j];
                    matriz[j] = aux;
                }

            }
        }
        matriz[izq] = matriz[j];
        matriz[j] = pivote;

        if (izq < j - 1)
        {
            realizarQuick(clazz, matriz, izq, j - 1, atributo, tipoOrdenacion);
        }

        if (j + 1 < der)
        {
            realizarQuick(clazz, matriz, j + 1, der, atributo, tipoOrdenacion);
        }

    }

    private Object[] evaluaCambiarDatoNoObjeto(Class clazz, E auxJ, E auxJ1, TipoOrdenacion tipoOrdenacion, Integer j, E[] matriz) throws Exception {
        Object aux[] = new Object[2];
        if (clazz.getSuperclass().getSimpleName().equalsIgnoreCase("Number"))
        {
            if (tipoOrdenacion.toString().equalsIgnoreCase(TipoOrdenacion.ASCENDENTE.toString()))
            {
                if ((((Number) auxJ).doubleValue() > ((Number) auxJ1).doubleValue()))
                {
                    aux[0] = auxJ1;
                    aux[1] = j;
                }
            } else
            {
                if ((((Number) auxJ).doubleValue() < ((Number) auxJ1).doubleValue()))
                {
                    aux[0] = auxJ1;
                    aux[1] = j;
                }
            }
        } else if (Utilidades.isString(clazz))
        {
            String datoJ = (String) auxJ;
            String datoJ1 = (String) auxJ1;
            if (tipoOrdenacion.toString().equalsIgnoreCase(TipoOrdenacion.ASCENDENTE.toString()))
            {
                if ((datoJ.toLowerCase().compareTo(datoJ1.toLowerCase()) > 0))
                {
                    aux[0] = auxJ1;
                    aux[1] = j;
                }
            } else
            {
                if ((datoJ.toLowerCase().compareTo(datoJ1.toLowerCase()) < 0))
                {
                    aux[0] = auxJ1;
                    aux[1] = j;
                }
            }

        } else if (Utilidades.isCharacter(clazz))
        {
            Character datoJ = (Character) auxJ;
            Character datoJ1 = (Character) auxJ1;
            if (tipoOrdenacion.toString().equalsIgnoreCase(TipoOrdenacion.ASCENDENTE.toString()))
            {
                if (datoJ > datoJ1)
                {
                    aux[0] = auxJ1;
                    aux[1] = j;
                }
            } else
            {
                if (datoJ < datoJ1)
                {
                    aux[0] = auxJ1;
                    aux[1] = j;
                }
            }

        }
        return aux;
    }

    /**
     * Permite hacer el cambio con datos que son objetos del modelo
     *
     * @param clazz El tipo de clase del atributo
     * @param auxJ dato en la posicion J
     * @param auxJ1 dato en la posicion J + 1
     * @param method El metodo get de J
     * @param method1 El metodo get de J + 1
     * @param tipoOrdenacion El tipo de ordenacion si es Ascendente o
     * Descendente
     * @param j posicion
     * @throws Exception
     */
    private Object[] evaluaCambiarDato(Class clazz, E auxJ, E auxJ1, Method method, Method method1, TipoOrdenacion tipoOrdenacion, Integer j, E[] matriz) throws Exception {
        Object aux[] = new Object[2];
        if (clazz.getSuperclass().getSimpleName().equalsIgnoreCase("Number"))
        {
            Number datoJ = (Number) method.invoke(auxJ);
            Number datoJ1 = (Number) method1.invoke(auxJ1);
            if (tipoOrdenacion.toString().equalsIgnoreCase(TipoOrdenacion.ASCENDENTE.toString()))
            {
                if ((datoJ.doubleValue() > datoJ1.doubleValue()))
                {
                    aux[0] = auxJ1;
                    aux[1] = j;
                }
            } else
            {
                if ((datoJ.doubleValue() < datoJ1.doubleValue()))
                {
                    aux[0] = auxJ1;
                    aux[1] = j;
                }
            }
        } else if (Utilidades.isString(clazz))
        {
            String datoJ = (String) method.invoke(auxJ);
            String datoJ1 = (String) method1.invoke(auxJ1);
            if (tipoOrdenacion.toString().equalsIgnoreCase(TipoOrdenacion.ASCENDENTE.toString()))
            {
                if ((datoJ.toLowerCase().compareTo(datoJ1.toLowerCase()) > 0))
                {
                    aux[0] = auxJ1;
                    aux[1] = j;
                }
            } else
            {
                if ((datoJ.toLowerCase().compareTo(datoJ1.toLowerCase()) < 0))
                {
                    aux[0] = auxJ1;
                    aux[1] = j;
                }
            }

        } else if (Utilidades.isCharacter(clazz))
        {
            Character datoJ = (Character) method.invoke(auxJ);
            Character datoJ1 = (Character) method1.invoke(auxJ1);
            if (tipoOrdenacion.toString().equalsIgnoreCase(TipoOrdenacion.ASCENDENTE.toString()))
            {
                if (datoJ > datoJ1)
                {
                    // cambioBurbuja(j, matriz);
                    aux[0] = auxJ1;
                    aux[1] = j;
                }
            } else
            {
                if (datoJ < datoJ1)
                {
                    //  cambioBurbuja(j, matriz);
                    aux[0] = auxJ1;
                    aux[1] = j;
                }
            }

        }
        return aux;
    }

    public ListaEnlazada<E> buscarDatoPosicionObjetoBinaria(String atributo, Object dato) throws Exception {
        Class<E> clazz = null;
        E[] matriz = null;
        ListaEnlazada<E> resultado = new ListaEnlazada<>();
        E aux = null;
        if (size > 0)
        {
            matriz = toArray();
            clazz = (Class<E>) cabecera.getDato().getClass();//primitivo, Dato envolvente, Object
            Boolean isObject = Utilidades.isObject(clazz);//si es objeto
            if (isObject)
            {
                Field field = Utilidades.getField(atributo, clazz);
                for (int i = 0; i < matriz.length; i++)
                {
                    Method method1 = getMethod("get" + Utilidades.capitalizar(atributo), matriz[i].getClass());
                    if (field.getType().getSuperclass().getSimpleName().equalsIgnoreCase("Number")
                            && dato.getClass().getSuperclass().getSimpleName().equalsIgnoreCase("Number"))
                    {
                        int inicio = 0;
                        int fin = matriz.length - 1;
                        int medio;
                        Number datoJ = (Number) dato;
                        Number datoJ1 = (Number) method1.invoke(matriz[i]);
                        while (inicio <= fin)
                        {
                            medio = (inicio + fin) / 2;
                            if (datoJ.doubleValue() == datoJ1.doubleValue())
                            {
                                aux = (E) matriz[i];
                                if (aux != null)
                                {
                                    resultado.insertar(aux);
                                }
                                break;
                            } else
                            {
                                if (datoJ1.doubleValue() < datoJ.doubleValue())
                                {
                                    inicio = medio + 1;
                                } else
                                {
                                    fin = medio - 1;
                                }
                            }
                        }
                    } else if (Utilidades.isString(field.getType()) && Utilidades.isString(dato.getClass()))
                    {
                        String datoJ = (String) dato;
                        String datoJ1 = (String) method1.invoke(matriz[i]);
                        int inicio = 0;
                        int fin = matriz.length - 1;
                        int medio = 0;
                        while (inicio <= fin)
                        {
                            medio = (inicio + fin) / 2;
                            if (datoJ1.toLowerCase().startsWith(datoJ.toLowerCase())
                                    || datoJ1.toLowerCase().endsWith(datoJ.toLowerCase())
                                    || datoJ1.toLowerCase().equalsIgnoreCase(datoJ.toLowerCase()))
                            {
                                aux = (E) matriz[i];
                                if (aux != null)
                                {
                                    resultado.insertar(aux);
                                }
                                break;
                            } else
                            {
                                if (datoJ1.toLowerCase().equalsIgnoreCase(datoJ.toLowerCase()))
                                {
                                    inicio = medio + 1;
                                } else
                                {
                                    fin = medio - 1;
                                }
                            }

                        }

                    } else if (Utilidades.isCharacter(field.getType()) && Utilidades.isCharacter(dato.getClass()))
                    {
                        Character datoJ = (Character) dato;
                        Character datoJ1 = (Character) method1.invoke(matriz[i]);
                        int inicio = 0;
                        int fin = matriz.length - 1;
                        int medio = 0;
                        while (inicio <= fin)
                        {
                            medio = (inicio + fin) / 2;
                            if (datoJ.charValue() == datoJ1.charValue())
                            {
                                aux = (E) matriz[i];
                                if (aux != null)
                                {
                                    resultado.insertar(aux);
                                }
                                break;
                            } else
                            {
                                if (datoJ1.charValue() < datoJ.charValue())
                                {
                                    inicio = medio + 1;
                                } else
                                {
                                    fin = medio - 1;
                                }

                            }

                        }

                    }

                }

            }
        }

        return resultado;
    }

    private E buscarDatoPosicion(Integer j, E[] matriz, Class<E> clazz, E dato) throws Exception {
        E aux = null;
        if (clazz.getSuperclass().getSimpleName().equalsIgnoreCase("Number"))
        {
            Number datoJ = (Number) dato;
            Number datoJ1 = (Number) matriz[j];
            if (datoJ.doubleValue() == datoJ1.doubleValue())
            {
                aux = (E) datoJ1;
            }
        } else if (Utilidades.isString(clazz))
        {
            String datoJ = (String) dato;
            String datoJ1 = (String) matriz[j];

            if (datoJ1.toLowerCase().startsWith(datoJ.toLowerCase())
                    || datoJ1.toLowerCase().endsWith(datoJ.toLowerCase())
                    || datoJ1.toLowerCase().equalsIgnoreCase(datoJ.toLowerCase()))
            {
                aux = (E) matriz[j];
            }

        } else if (Utilidades.isCharacter(clazz))
        {
            Character datoJ = (Character) dato;
            Character datoJ1 = (Character) matriz[j];
            if (datoJ.charValue() == datoJ1.charValue())
            {
                aux = (E) matriz[j];
            }

        }
        return aux;
    }

    private E buscarDatoPosicionObjeto(Integer j, E[] matriz, Class clazz, Object dato, Method method1) throws Exception {
        E aux = null;
        if (clazz.getSuperclass().getSimpleName().equalsIgnoreCase("Number")
                && dato.getClass().getSuperclass().getSimpleName().equalsIgnoreCase("Number"))
        {
            Number datoJ = (Number) dato;
            Number datoJ1 = (Number) method1.invoke(matriz[j]);
            if (datoJ.doubleValue() == datoJ1.doubleValue())
            {
                aux = (E) matriz[j];
            }
        } else if (Utilidades.isString(clazz) && Utilidades.isString(dato.getClass()))
        {
            String datoJ = (String) dato;
            String datoJ1 = (String) method1.invoke(matriz[j]);

            if (datoJ1.toLowerCase().startsWith(datoJ.toLowerCase())
                    || datoJ1.toLowerCase().endsWith(datoJ.toLowerCase())
                    || datoJ1.toLowerCase().equalsIgnoreCase(datoJ.toLowerCase()))
            {
                aux = (E) matriz[j];
            }

        } else if (Utilidades.isCharacter(clazz) && Utilidades.isCharacter(dato.getClass()))
        {
            Character datoJ = (Character) dato;
            Character datoJ1 = (Character) method1.invoke(matriz[j]);
            if (datoJ.charValue() == datoJ1.charValue())
            {
                aux = (E) matriz[j];
            }

        }
        return aux;
    }
}
