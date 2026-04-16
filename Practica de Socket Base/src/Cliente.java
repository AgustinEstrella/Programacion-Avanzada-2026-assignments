import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) throws Exception {
        
        Scanner ingreso = new Scanner(System.in);

        final String HOST = "172.17.2.125";
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

            String ingresoNombre = in.readUTF();
            System.out.println(ingresoNombre);

            String nombre = ingreso.nextLine();
            out.writeUTF(nombre);
            
            ClienteHilo hilo = new ClienteHilo(in, out);
            hilo.start();
            hilo.join(); 

            
            System.out.println("Ingrese su formula");
            String miFormula = ingreso.nextLine();
            out.writeUTF(miFormula);

            String mensaje = in.readUTF();
            System.out.println(mensaje);

            

            sc.close();

        } catch (IOException e) {
        // TODO Auto-generated catch block
            e.printStackTrace();
        }   

    }
}