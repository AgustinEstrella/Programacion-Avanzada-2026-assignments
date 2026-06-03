package com.mycompany.solidplataformaeduvirtual;
import java.sql.Connection;
import java.util.Scanner;
public class SolidPlataformaEduVirtual {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Objeto para trabajar con la tabla estudiantes
        EstudianteBd estudianteBd = new EstudianteBd();

        int opcion;

        do {

            System.out.println("\n===== PLATAFORMA EDUCATIVA =====");

            System.out.println("1. Registrar estudiante");
            System.out.println("2. Listar estudiantes");
            System.out.println("3. Calcular nota");
            System.out.println("0. Salir");

            System.out.print("Opcion: ");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {

                case 1:

                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();

                    System.out.print("Email: ");
                    String email = sc.nextLine();

                    // Crea el estudiante
                    Estudiante estudiante =
                            new Estudiante(nombre, email);

                    // Lo guarda en MySQL
                    estudianteBd.guardar(estudiante);

                    break;

                case 2:

                    // Muestra todos los estudiantes
                    estudianteBd.listar();

                    break;

                case 3:

                    System.out.println("\nTIPO DE EVALUACION");

                    System.out.println("1. Examen");
                    System.out.println("2. Trabajo Practico");
                    System.out.println("3. Proyecto Final");

                    int tipo = sc.nextInt();

                    System.out.print("Nota: ");

                    double nota = sc.nextDouble();

                    // Gracias a OCP podemos usar distintos tipos
                    Evaluacion evaluacion;

                    if (tipo == 1) {

                        evaluacion = new Examen();

                    } else if (tipo == 2) {

                        evaluacion = new TrabajoPractico();

                    } else {

                        evaluacion = new ProyectoFinal();
                    }

                    double resultado =
                            evaluacion.calcularNotaFinal(nota);

                    System.out.println(
                            "Nota final: "
                            + resultado
                    );

                    break;

                case 0:

                    System.out.println("Programa finalizado");

                    break;

                default:

                    System.out.println("Opcion incorrecta");
            }

        } while (opcion != 0);

        sc.close();
    }
    
}
    

