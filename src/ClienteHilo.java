import java.io.*;
import java.util.Scanner;

public class ClienteHilo extends Thread {

    private DataInputStream in;
    private DataOutputStream out;

    public ClienteHilo(DataInputStream in, DataOutputStream out) {
        this.in = in;
        this.out = out;
    }

    @Override
    public void run() {

        Scanner sc = new Scanner(System.in);

        boolean salir = false;

        while (!salir) {

            try {
                // Menú de opciones
                System.out.println("\n1: Hora");
                System.out.println("2: Resolver operación");
                System.out.println("3: Ver clientes");
                System.out.println("4: Enviar mensaje");

                int opcion = sc.nextInt();
                sc.nextLine();

                // Enviar opción al servidor
                out.writeInt(opcion);

                switch (opcion) {

                    case 1:
                        System.out.println("Hora: " + in.readUTF());
                        break;

                    case 2:
                        System.out.println("Ingrese operación (ej: RESOLVE \"5+3*2\"):");
                        String op = sc.nextLine();
                        out.writeUTF(op);
                        System.out.println(in.readUTF());
                        break;

                    case 3:
                        System.out.println(in.readUTF());
                        break;

                    case 4:
                        System.out.println("Mensaje:");
                        String msg = sc.nextLine();
                        out.writeUTF(msg);
                        break;
                }

            } catch (IOException e) {
                e.printStackTrace();
                salir = true;
            }
        }
    }
}
