import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import java.time.LocalDateTime; //Para mostrar la hora
import java.time.format.DateTimeFormatter; // Para darle formato a la hora


public class ServidorHilo extends Thread{
        
        private DataInputStream in;
        private DataOutputStream out;
        private String nombreCliente;


        public ServidorHilo(DataInputStream in, DataOutputStream out, String nombreCliente){
            this.in = in;
            this.out = out;
            this.nombreCliente = nombreCliente;
        }

        @Override
        public void run(){

            int opcion;

            while (true) {
                
                try {
                    opcion = in.readInt();

                    switch (opcion) {
                        case 1:
                            LocalDateTime base = LocalDateTime.now(); //Creo la base
                            DateTimeFormatter elFormato = DateTimeFormatter.ofPattern("E, MMM dd yyyy HH:mm:ss"); //Creo el formato
                            String horaConFormato = base.format(elFormato);
                            out.writeUTF(horaConFormato);
                            break;
                        
                        case 2:
                            String funcion = in.readUTF();
                            if (funcion.trim().toUpperCase().startsWith("RESOLVE")){
                                String resultado = calculadoraFunciones(funcion);
                                out.writeUTF("Resultado de la operacion: " + resultado);
                            } else {
                                break;
                            } 
                            
                        case 3:
                            
                            break;
                            
                        case 4:
                            
                            break;

                        default:
                            out.writeUTF("Solo numeros del 1 al 4");
                            break;
                    }


                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            
        }
    
    // Extrae la ecuación de las comillas y llama al evaluador
    private static String calculadoraFunciones(String input) {
        try {
            int primerComilla = input.indexOf('"');
            int ultimaComilla = input.lastIndexOf('"');
            
            if (primerComilla == -1 || ultimaComilla == -1 || primerComilla == ultimaComilla) {
                return "Error de sintaxis. Usa: RESOLVE \"45*23/54+234\"";
            }
            
            // Extrae solo lo que está entre comillas y quita espacios en blanco
            String ecuacion = input.substring(primerComilla + 1, ultimaComilla).replace(" ", "");

            double resultado = resolverEcuacion(ecuacion);
            
            return String.valueOf(resultado);
            
        } catch (Exception e) {
            return "Error al calcular. Verifica que solo haya numeros y operadores basicos.";
        }
    }

    // Priorizar multiplicaciones/divisiones
    private static double resolverEcuacion(String ecuacion) {
        List<Double> numeros = new ArrayList<>();
        List<Character> operadores = new ArrayList<>();
        
        //Parsear el string para separar numeros de operadores
        StringBuilder tempNum = new StringBuilder();
        //Recorrer toda la ecuacion
        for (int i = 0; i < ecuacion.length(); i++) {
            char c = ecuacion.charAt(i);
            if (c == '-' && tempNum.length() == 0) {
                tempNum.append(c);
            } else if (Character.isDigit(c) || c == '.') {
                tempNum.append(c);
            } else if (c == '+' || c == '-' || c == '*' || c == '/') {
                numeros.add(Double.parseDouble(tempNum.toString()));
                operadores.add(c);
                tempNum.setLength(0); // Limpiar el buffer numerico
            }
        } 
        numeros.add(Double.parseDouble(tempNum.toString())); // Agregar el ultimo numero
        
        //Resolver multiplicaciones y divisiones
        for (int i = 0; i < operadores.size(); i++) {
            char op = operadores.get(i);
            if (op == '*' || op == '/') {
                double a = numeros.get(i);
                double b = numeros.get(i + 1);
                double resultado = (op == '*') ? (a * b) : (a / b);
                
                numeros.set(i, resultado); // Reemplazar el primer numero por el resultado
                numeros.remove(i + 1);     // Borrar el segundo numero
                operadores.remove(i);      // Borrar el operador usado
                i--; // Retroceder el indice porque las listas se achicaron
            }
        }
        
        //Resolver sumas y restas de izquierda a derecha
        double resultadoFinal = numeros.get(0);
        for (int i = 0; i < operadores.size(); i++) {
            char op = operadores.get(i);
            double sigNumero = numeros.get(i + 1);
            if (op == '+') resultadoFinal += sigNumero;
            if (op == '-') resultadoFinal -= sigNumero;
        }
        
        return resultadoFinal;
    }

}