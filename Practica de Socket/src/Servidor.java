import java.io.*;
import java.net.*;
import java.util.*;

public class Servidor {

    // Lista global donde guardamos todos los clientes conectados
    public static List<ServidorHilo> clientes = new ArrayList<>();

    public static void main(String[] args) {

        final int PUERTO = 6000;

        try {
            // Se crea el servidor en el puerto indicado
            ServerSocket servidor = new ServerSocket(PUERTO);
            System.out.println("Servidor iniciado...");

            // Bucle infinito para aceptar múltiples clientes
            while (true) {

                // Espera a que un cliente se conecte
                Socket socket = servidor.accept();

                // Flujos de entrada y salida de datos
                DataInputStream in = new DataInputStream(socket.getInputStream());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());

                // Se le pide el nombre al cliente
                out.writeUTF("Escribe tu nombre:");
                String nombre = in.readUTF();

                // Se crea un hilo para manejar a ese cliente
                ServidorHilo hilo = new ServidorHilo(socket, in, out, nombre);

                // Se guarda en la lista de clientes
                clientes.add(hilo);

                // Se inicia el hilo
                hilo.start();

                System.out.println("Cliente conectado: " + nombre);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}