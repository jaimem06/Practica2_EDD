package TDA_Lista;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 *
 * @author Jaime Mendoza
 */
public class Utilidades {

    public static Field getField(String name, Class clazz) {
        Field[] fields = clazz.getDeclaredFields();
        Field aux = null;
        for (int i = 0; i < fields.length; i++)
        {

            if (name.toString().equalsIgnoreCase(fields[i].getName()))
            {
                aux = fields[i];
                break;
            }

        }
        if (aux != null)
        {
            return aux;
        }

        fields = clazz.getSuperclass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++)
        {

            if (name.toString().equalsIgnoreCase(fields[i].getName()))
            {
                aux = fields[i];
                break;
            }

        }

        return aux;
    }

    public static Method getMethod(String name, Class clazz) {

        Method[] methods = clazz.getDeclaredMethods();
        Method aux = null;

        for (int i = 0; i < methods.length; i++)
        {

            if (name.toString().equalsIgnoreCase(methods[i].getName()))
            {
                aux = methods[i];
                break;
            }

        }
        if (aux != null)
        {
            return aux;
        }
        methods = clazz.getSuperclass().getDeclaredMethods();
        for (int i = 0; i < methods.length; i++)
        {

            if (name.toString().equalsIgnoreCase(methods[i].getName()))
            {
                aux = methods[i];
                break;
            }

        }
        return aux;
    }

    public static Object cambiarDatos(Object dato, String field, Object a) throws Exception {

        Field fieldA = getField(field, a.getClass());
        char[] arr = field.toCharArray();
        arr[0] = Character.toUpperCase(arr[0]);
        Method method = getMethod("set" + new String(arr), a.getClass());
        try
        {
            if (fieldA.getType().getSuperclass().getSimpleName().equalsIgnoreCase("Number"))
            {
                method.invoke(a, transformarDatoNumber(fieldA.getType(), dato.toString()));
            } else if (fieldA.getType().isEnum())
            {
              
                Enum enume = Enum.valueOf((Class) fieldA.getType(), dato.toString());
                method.invoke(a, enume);
            } else if (fieldA.getType().getSimpleName().equalsIgnoreCase("Boolean"))
            {

                method.invoke(a, dato.toString().equalsIgnoreCase("true"));
            } else
            {
                method.invoke(a, dato);
            }

        } catch (Exception e)
        {
            System.out.println(e + "  " + field);
        }
        return a;

    }

    public static Number transformarDatoNumber(Class type, String dato) {
        Number number = null;
        if (type.getSimpleName().equalsIgnoreCase(Integer.class.getSimpleName()))
        {
            number = Integer.parseInt(dato);
        }
        if (type.getSimpleName().equalsIgnoreCase(Double.class.getSimpleName()))
        {
            number = Double.parseDouble(dato);
        }
        if (type.getSimpleName().equalsIgnoreCase(Float.class.getSimpleName()))
        {
            number = Float.parseFloat(dato);
        }
        if (type.getSimpleName().equalsIgnoreCase(Short.class.getSimpleName()))
        {
            number = Short.parseShort(dato);
        }
        return number;
    }

    public static Boolean isNumber(Class clase) {
        return clase.getSuperclass().getSimpleName().equalsIgnoreCase("Number");
    }

    public static Boolean isString(Class clase) {
        return clase.getSimpleName().equalsIgnoreCase("String");
    }

    public static Boolean isCharacter(Class clase) {
        return clase.getSimpleName().equalsIgnoreCase("Character");
    }

    public static Boolean isBoolean(Class clase) {
        return clase.getSimpleName().equalsIgnoreCase("Boolean");
    }

    public static Boolean isPrimitivo(Class clase) {
        return clase.isPrimitive();
    }

    public static Boolean isObject(Class clase) {
        return (!isPrimitivo(clase) && !isBoolean(clase) && !isCharacter(clase) && !isString(clase) && !isNumber(clase));
    }

    public static String capitalizar(String field) {
        char[] arr = field.toCharArray();
        arr[0] = Character.toUpperCase(arr[0]);
        return new String(arr);
    }

}
