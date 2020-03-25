//Imports necesarios para el funcionamiento del programa

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Historial {
  public static void main(String[] args) {

    //Controla los parámetros introducidos
    if (args.length != 1 && args.length != 2) {
      System.out.println("No se han introducido los parámetros correctamente");
      System.out.println("Escribe: java Historial [parámetro] [nombreWeb]");
      System.out.println("Los parámetros disponibles son crear, borrar y mostrar");
      System.exit(-1);
    }

    //Crea el ArrayList donde se almacenarán las webs visitadas
    //Necesitará un archivo webs.md para funcionar
    ArrayList<String> historial = new ArrayList<>();
    try {
      BufferedReader br = new BufferedReader(new FileReader("webs.md"));
      String entrada;
      while ((entrada = br.readLine()) != null) {
        historial.add(entrada);
      }
      br.close();
    } catch (FileNotFoundException error) { //En caso de no existir el archivo
      System.out.println("Crea el archivo webs.md para poder usar este programa");
      System.exit(-1);
    } catch (IOException error) {
      error.printStackTrace();
    }

    //Funcionamiento de los parámetros
    switch (args[0]) {
      case "mostrar":
        System.out.println(historial);
        break;
      case "crear":
        try {
          BufferedWriter bw = new BufferedWriter(new FileWriter("webs.md"));
          String salida = "";
          for (String linea : historial) {
            salida += linea;
            salida += "\n";
          }
          salida += args[1];
          bw.write(salida);
          bw.close();
        } catch (IOException error) {
          error.printStackTrace();
        }
        break;
      case "borrar":
        historial.remove(args[1]);
        try {
          BufferedWriter bw = new BufferedWriter(new FileWriter("webs.md"));
          String salida = "";
          for (String linea : historial) {
            salida += linea;
            salida += "\n";
          }
          bw.write(salida);
          bw.close();
        } catch (IOException error) {
          error.printStackTrace();
        }
        break;
      default:
        System.out.println(args[0] + " no es un parámetro válido");
        System.out.println("Los parámetros usables son crear, borrar y mostrar");
    }
  }
}
