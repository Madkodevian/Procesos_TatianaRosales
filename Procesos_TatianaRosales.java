
package procesos_tatianarosales;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Procesos_TatianaRosales {

    public static void main(String[] args) {

        //APARTADO1a
        //procesoPrueba1a("cmd", "/c", "tasklist");
        //APARTADO 1b
        //procesoPrueba1b("cmd", "/c", "ping google.es");
        //APARTADO2
        //procesoPrueba2("cmd", "/c", "ping google.es");
        //APARTADO3
        //procesoPrueba3("cmd", "/c", "ping google.es");
        //APARTADO4
        //procesoPrueba4("cmd", "/c", "dir");
        //APARTADO5
        //procesoPrueba5("cmd", "/c", "type fichero.txt");
        //procesoPrueba5("cmd", "/c", "find dias fichero.txt");
        //procesoPrueba5("cmd", "/find", "fichero.txt");
    }

    ////////////////////////////////////////////////////////////////
    //EJECUTA COMANDO
    private static void procesoPrueba1a(String command, String arg1, String arg2) {
        //Prueba procesos
        /**
         * PARTE 1.a)Se le pasa por parámetro el comando y los argumentos y el
         * metodo crea un proceso que ejecuta dicho comando con sus argumentos
         */

        ArrayList<String> listaElem = new ArrayList<>();
        listaElem.add(command);
        listaElem.add(arg1);
        listaElem.add(arg2);

        // Crear el process builder
        ProcessBuilder pba = new ProcessBuilder(listaElem);
        pba.inheritIO();
        System.out.println(listaElem);

        try {
            Process p = pba.start();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    //EJECUTA COMANDO
    private static void procesoPrueba1b(String command, String arg1, String arg2) {
        //Prueba procesos
        /**
         * PARTE 1.b)Modifica el método anterior para que en este caso el
         * proceso padre espera a que finalice el proceso hijo. Si la ejecución
         * del comando no se realiza correctamente muestra un mensaje de error.
         */

        ArrayList<String> listaElem = new ArrayList<>();
        listaElem.add(command);
        listaElem.add(arg1);
        listaElem.add(arg2);

        // Crear el process builder
        ProcessBuilder pbb = new ProcessBuilder(listaElem);
        pbb.inheritIO();
        System.out.println(listaElem);

        try {
            Process p = pbb.start();
            p.waitFor();
        } catch (IOException | InterruptedException e) {
            System.out.println("Error al realizar el proceso.");
        }
    }

    //EJECUTA COMANDO Y COMPRUEBA
    private static void procesoPrueba2(String command, String arg1, String arg2) {
        //Prueba procesos
        /**
         * PARTE 2.Se le pasa por parámetro el comando y los argumentos y el
         * método crea un proceso que ejecuta dicho comando con sus argumentos.
         * Cada x segundos comprueba si el proceso ha finalizado y muestra el
         * mensaje "Esperando..." en cada comprobación.
         */

        ArrayList<String> listaElem = new ArrayList<>();
        listaElem.add(command);
        listaElem.add(arg1);
        listaElem.add(arg2);

        // Crear el process builder
        ProcessBuilder pb2 = new ProcessBuilder(listaElem);
        pb2.inheritIO();
        System.out.println(listaElem);

        try {
            Process p = pb2.start();
            while (p.isAlive()) {
                p.waitFor(3, TimeUnit.SECONDS);
                System.out.println("Esperando...");
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("Error al realizar el proceso.");
        }
    }

    //EJECUTA COMANDO Y ESPERA
    private static void procesoPrueba3(String command, String arg1, String arg2) {
        //Prueba procesos
        /**
         * PARTE 3.se le pasa por parámetro el comando y los argumentos y el
         * método crea un proceso que ejecuta dicho comando con sus argumentos.
         * Espera a que finalice su ejecución durante 5 segundos. En caso de que
         * no lo haga, destruye el proceso hijo y finaliza su ejecución.
         */

        ArrayList<String> listaElem = new ArrayList<>();
        listaElem.add(command);
        listaElem.add(arg1);
        listaElem.add(arg2);

        // Crear el process builder
        ProcessBuilder pb3 = new ProcessBuilder(listaElem);
        pb3.inheritIO();
        System.out.println(listaElem);

        try {
            Process p = pb3.start();
            p.waitFor(5, TimeUnit.SECONDS);
            p.destroy();
        } catch (IOException | InterruptedException e) {
            System.out.println("Error al realizar el proceso.");
        }
    }

    //EJECUTA COMANDO DIRECTORIO
    private static void procesoPrueba4(String command, String arg1, String arg2) {
        //Prueba procesos
        /**
         * PARTE 4.se le pasa por parámetro el comando y los argumentos, junto
         * al directorio de ejecución. El método debe ejecutar dicho comando en
         * dicho directorio. La salida debe demostrar que se ha realizado la
         * ejecución en el directorio.
         */

        //processbuilder directory
        ArrayList<String> listaElem = new ArrayList<>();
        listaElem.add(command);
        listaElem.add(arg1);
        listaElem.add(arg2);

        // Crear el process builder
        ProcessBuilder pb4 = new ProcessBuilder(listaElem);
        pb4.inheritIO();
        System.out.println(listaElem);

        try {
            pb4.directory(new File("C:/"));
            Process p = pb4.start();
            System.out.println("" + pb4.directory());
        } catch (IOException e) {
            System.out.println("Error al realizar el proceso.");
        }
    }

    //BUSCA Y GUARDA
    private static void procesoPrueba5(String command, String arg1, String arg2) {
        //Prueba procesos
        /**
         * PARTE 5.este método debe ejecutar un proceso que busque mediante el
         * comando "grep" (o "find" en Windows) el texto pasado por parámetro en
         * el primer fichero pasado por parámetro (hay que redirigir la entrada
         * estándar del proceso hijo a ese fichero). La salida que genera el
         * comando grep/find se debe redirigir al segundo fichero pasado por
         * parámetro.
         */

        //processbuilder directory
        ArrayList<String> listaElem = new ArrayList<>();
        listaElem.add(command);
        listaElem.add(arg1);
        listaElem.add(arg2);

        // Crear el process builder
        ProcessBuilder pb5 = new ProcessBuilder(listaElem);
        pb5.inheritIO();
        //System.out.println(listaElem);

        try {
            pb5.directory(new File("F:/"));
            Process p = pb5.start();

            //Crear dos ficheros: uno de entrada y otro de salida
            try ( InputStream inputStream = new FileInputStream("F:\\fichero.txt")) {
                byte[] array = new byte[100];
                // Read byte from the input stream
                inputStream.read(array);
                // Convert byte array into string
                String data = new String(array);

                System.out.println(data);
                // Close the input stream
                inputStream.close();
            }
            
            // --------------------------//
            
            try ( FileOutputStream fout = new FileOutputStream("F:\\fichero.txt", true)) {
                String st = "";

                char ch[] = st.toCharArray();
                System.out.println(ch);

                /**
                int i;
                for (i = 0; i < st.length(); i++) {
                    //fout.write(ch[i]);
                    //System.out.println();
                }
                */
                fout.close();
            }

        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
