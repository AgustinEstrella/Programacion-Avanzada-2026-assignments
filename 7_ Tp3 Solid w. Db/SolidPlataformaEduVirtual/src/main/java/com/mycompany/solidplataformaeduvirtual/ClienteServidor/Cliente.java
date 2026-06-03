package com.mycompany.solidplataformaeduvirtual.ClienteServidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Cliente {
    public static void main(String[] args) {

        try {
            Socket socket = new Socket("localhost", 5000);
            System.out.println("Conectado al servidor exitosamente.");

            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            ClienteHilo clienteHilo = new ClienteHilo(in, out);
            clienteHilo.start();

            clienteHilo.join();

            socket.close();
            System.out.println("Conexión finalizada.");

        } catch(Exception e){
            System.err.println("Error al conectar con el servidor: " + e.getMessage());
        }
    }
}