import java.io.DataInputStream;
import java.io.DataOutputStream;

import java.io.IOException;

import java.util.Scanner;


public class ClienteHilo extends Thread {
            
        private DataInputStream in;
        private DataOutputStream out;

        public ClienteHilo(DataInputStream in, DataOutputStream out){
            this.in = in;
            this.out = out;
        }

        @Override
        public void run(){
            Scanner sc = new Scanner(System.in);
            int opcion = 0;
            String mensaje;

            boolean salir = true; 

            while (!salir) {
                try {
                        System.out.println("1: Consultar la fecha y hora");
                        System.out.println("2: Resolver expresión matemática");
                        System.out.println("3: Listar clientes conectados");
                        System.out.println("4: Mandar mensaje a todos los clientes");
                        opcion = sc.nextInt();
                        out.writeInt(opcion);

                        switch (opcion) {
                            case 1:
                                sc.nextLine(); //Evitar errores
                                String horario = in.readUTF();
                                System.out.println("Dia y hora: " +horario);                                
                                break;

                            case 2:
                                sc.nextLine();
                                //Le enviamos la funcion al servidor
                                System.out.println("Ingrese su formula");
                                String miFormula = sc.nextLine();
                                out.writeUTF(miFormula);

                                //Recibimos el resultado de la funcion
                                String resultado = in.readUTF();
                                System.out.println(resultado);
                                break;

                            case 3:
                                sc.nextLine(); //Evitar errores

                                break;

                            case 4:
                                sc.nextLine(); //Evitar errores  

                                break;

                            default:
                                mensaje = in.readUTF();
                                System.out.println(mensaje);
                                break;                      
                        }

                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
            }
        }

}   
