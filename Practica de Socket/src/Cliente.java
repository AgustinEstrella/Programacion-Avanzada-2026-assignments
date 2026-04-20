import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        final String HOST = "localhost";
        final int PUERTO = 6000;

        try {
            // Se conecta al servidor
            Socket sc = new Socket(HOST, PUERTO);

            DataInputStream in = new DataInputStream(sc.getInputStream());
            DataOutputStream out = new DataOutputStream(sc.getOutputStream());

            // Recibe mensaje de bienvenida
            String bienvenida = in.readUTF();
            System.out.println(bienvenida);

            // Envía nombre
            String nombre = input.nextLine();
            out.writeUTF(nombre);

            // Se crea el hilo del cliente
            ClienteHilo hilo = new ClienteHilo(in, out);
            hilo.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}