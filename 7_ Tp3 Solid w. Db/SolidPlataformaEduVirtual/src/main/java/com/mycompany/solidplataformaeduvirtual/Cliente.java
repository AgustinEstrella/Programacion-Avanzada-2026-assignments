package com.mycompany.solidplataformaeduvirtual;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Cliente {
    public static void main(String[] args) {

        try {
            Socket socket =
                    new Socket("localhost", 5000);

            PrintWriter salida =
                    new PrintWriter(
                            socket.getOutputStream(), true);

            BufferedReader entrada =
                    new BufferedReader(
                            new InputStreamReader(
                                    socket.getInputStream()
                            )
                    );

            salida.println("Hola servidor");

            System.out.println(entrada.readLine());

            socket.close();

        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
