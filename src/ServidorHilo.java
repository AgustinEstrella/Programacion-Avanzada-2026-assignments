import java.io.*;
import java.net.Socket;
import java.util.*;
import java.time.LocalDateTime;

public class ServidorHilo extends Thread {

    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private String nombreCliente;

    // Constructor
    public ServidorHilo(Socket socket, DataInputStream in, DataOutputStream out, String nombreCliente) {
        this.socket = socket;
        this.in = in;
        this.out = out;
        this.nombreCliente = nombreCliente;
    }

    @Override
    public void run() {

        try {
            // Bucle para escuchar constantemente al cliente
            while (true) {

                // Recibe la opción elegida por el cliente
                int opcion = in.readInt();

                switch (opcion) {

                    case 1:
                        enviarHora();
                        break;

                    case 2:
                        resolverOperacion();
                        break;

                    case 3:
                        listarClientes();
                        break;

                    case 4:
                        enviarMensajeATodos();
                        break;

                    default:
                        out.writeUTF("Opción inválida");
                }
            }

        } catch (IOException e) {
            // Si el cliente se desconecta, lo eliminamos de la lista
            System.out.println(nombreCliente + " se desconectó.");
            Servidor.clientes.remove(this);
        }
    }

    // ---------------- FUNCIONES ----------------

    // Envía la fecha y hora actual
    private void enviarHora() throws IOException {
        String hora = LocalDateTime.now().toString();
        out.writeUTF(hora);
    }

    // Recibe una operación matemática y la resuelve
    private void resolverOperacion() throws IOException {
        String funcion = in.readUTF();
        String resultado = calculadoraFunciones(funcion);
        out.writeUTF("Resultado: " + resultado);
    }

    // Envía la lista de clientes conectados
    private void listarClientes() throws IOException {
        StringBuilder lista = new StringBuilder("Clientes conectados:\n");

        for (ServidorHilo c : Servidor.clientes) {
            lista.append("- ").append(c.nombreCliente).append("\n");
        }

        out.writeUTF(lista.toString());
    }

    // Envía un mensaje a todos los clientes conectados
    private void enviarMensajeATodos() throws IOException {
        String mensaje = in.readUTF();

        for (ServidorHilo c : Servidor.clientes) {
            c.out.writeUTF(nombreCliente + ": " + mensaje);
        }
    }

    // ---------------- CALCULADORA ----------------

    // Procesa el comando RESOLVE
    private static String calculadoraFunciones(String input) {
        try {
            int p1 = input.indexOf('"');
            int p2 = input.lastIndexOf('"');

            if (p1 == -1 || p2 == -1) {
                return "Error de sintaxis. Usa: RESOLVE \"5+3*2\"";
            }

            // Extrae la expresión matemática
            String ecuacion = input.substring(p1 + 1, p2);

            // La resuelve
            double resultado = resolverEcuacion(ecuacion);

            return String.valueOf(resultado);

        } catch (Exception e) {
            return "Error al calcular";
        }
    }

    // Resuelve la ecuación respetando prioridad de operadores
    private static double resolverEcuacion(String ecuacion) {

        List<Double> numeros = new ArrayList<>();
        List<Character> operadores = new ArrayList<>();

        String numero = "";

        // Separa números y operadores
        for (char c : ecuacion.toCharArray()) {

            if ("+-*/".indexOf(c) >= 0) {
                numeros.add(Double.parseDouble(numero));
                operadores.add(c);
                numero = "";
            } else {
                numero += c;
            }
        }

        numeros.add(Double.parseDouble(numero));

        // Primero resuelve multiplicación y división
        for (int i = 0; i < operadores.size(); i++) {

            char op = operadores.get(i);

            if (op == '*' || op == '/') {

                double a = numeros.get(i);
                double b = numeros.get(i + 1);

                double resultado = (op == '*') ? a * b : a / b;

                numeros.set(i, resultado);
                numeros.remove(i + 1);
                operadores.remove(i);

                i--;
            }
        }

        // Luego suma y resta
        double resultadoFinal = numeros.get(0);

        for (int i = 0; i < operadores.size(); i++) {

            if (operadores.get(i) == '+')
                resultadoFinal += numeros.get(i + 1);
            else
                resultadoFinal -= numeros.get(i + 1);
        }

        return resultadoFinal;
    }
}