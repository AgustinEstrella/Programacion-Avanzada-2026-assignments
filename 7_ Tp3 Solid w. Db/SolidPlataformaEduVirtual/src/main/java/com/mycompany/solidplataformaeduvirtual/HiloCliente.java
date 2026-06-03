package com.mycompany.solidplataformaeduvirtual;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class HiloCliente extends Thread{
    private Socket socket;

    public HiloCliente(Socket socket) {

        this.socket = socket;
    }

    @Override
    public void run() {

        try {

            BufferedReader entrada =
                    new BufferedReader(
                            new InputStreamReader(
                                    socket.getInputStream()
                            )
                    );

            PrintWriter salida =
                    new PrintWriter(
                            socket.getOutputStream(),
                            true
                    );

            String mensaje =
                    entrada.readLine();

            System.out.println(
                    "Mensaje recibido: "
                    + mensaje
            );

            salida.println(
                    "Mensaje recibido correctamente"
            );

            socket.close();

        } catch(Exception e){

            e.printStackTrace();
        }
    }
    
}
