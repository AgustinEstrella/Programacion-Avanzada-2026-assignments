import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) throws Exception {
        
        Scanner input = new Scanner(System.in);

        final String HOST = "172.16.0.2";
        final int PUERTO = 6000;

        //Variable de entrada de datos
        DataInputStream in;

        //Variable de salida de datos
        DataOutputStream out;

        try {

            Socket sc = new Socket(HOST, PUERTO);

            //Inicializacion entrada de datos
            in = new DataInputStream(sc.getInputStream());

            //Inicializacion salida de datos
            out = new DataOutputStream(sc.getOutputStream());

            //Mostramos el mensaje que nos manda el servidor
            String bienvenida = in.readUTF();
            System.out.println(bienvenida);

            //Le mandamos al servidor el nombre
            String nombre = input.nextLine();
            out.writeUTF(nombre);

            //Creamos el hilo
            ClienteHilo hilo = new ClienteHilo(in, out);
            hilo.start();
            hilo.join(); 
        
            //sc.close();

        } catch (IOException e) {
        // TODO Auto-generated catch block
            e.printStackTrace();
        }   

    }
}