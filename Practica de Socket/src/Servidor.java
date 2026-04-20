import java.io.DataInputStream;
import java.io.DataOutputStream;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import java.util.ArrayList;
import java.util.List;


public class Servidor {

    public static void main(String[] args) throws Exception{
        
        ServerSocket servidor = null;
        Socket miSocket = null;

        final int PUERTO = 6000;
        

            try {

                servidor = new ServerSocket(PUERTO);
                System.out.println("Servidor inciado, esperando al cliente");

                boolean conectado = true;
                while (conectado) {
                    miSocket = servidor.accept();

                    //A partir de aca implementamos hilos
                    DataInputStream in = new DataInputStream(miSocket.getInputStream());
                    DataOutputStream out = new DataOutputStream(miSocket.getOutputStream());

                    //Mensaje que le envia el servidor al cliente
                    out.writeUTF("Escribe tu nombre");  
                    String nombreCliente = in.readUTF();

                    ServidorHilo hilo = new ServidorHilo(in, out, nombreCliente);
                    hilo.start();

                    System.out.println("Creada la conexion con el cliente: " +nombreCliente);
                    //---------------------------------------------------------------------//
                    
    
                    System.out.println("Esperando instrucciones del cliente");

                    //Lee el mensaje enviado por el cliente
                    String mensaje = in.readUTF();
                    
                    miSocket.close();

                    System.out.println("Cliente desconectado");
                }

            } catch (IOException ex) {
                // TODO Auto-generated catch block
                ex.printStackTrace();
            } 
    }


}