package Vista;

import Controlador.Controlador_Comida;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

/**
 *
 * @author Jaime Mendoza
 */
public class Utilidades {

    public static boolean guardarArchivoJSON(Controlador_Comida ctrlComida) {
        Gson gson = new Gson();
        String json = gson.toJson(ctrlComida);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("DatosGenerados.json")))
        {
            bw.write(json);
            return true;
        } catch (Exception e)
        {
            System.out.println("Error" + e);
            return false;
        }

    }

    public static Controlador_Comida cargarArchivoJSON() {
        String json = "";
        Gson gson = new Gson();

        try
        {
            BufferedReader br = new BufferedReader(new FileReader("DatosGenerados.json"));
            String linea = "";
            while ((linea = br.readLine()) != null)
            {
                json += linea;
            }

            br.close();
        } catch (Exception e)
        {
            System.out.println("Error" + e);
        }

        Controlador_Comida ctrlComida = gson.fromJson(json, Controlador_Comida.class);
        return ctrlComida;
    }

}
