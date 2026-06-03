package com.mycompany.solidplataformaeduvirtual;

import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
     public static void main(String[] args) {

        try {
            ServerSocket servidor = new ServerSocket(5000);

            System.out.println("Servidor iniciado");

            while(true){

                Socket cliente = servidor.accept();

                System.out.println("Cliente conectado");

                HiloCliente hilo = new HiloCliente(cliente);

                hilo.start();
            }

        } catch(Exception e){

            e.printStackTrace();
        }
    }
}