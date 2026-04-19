//mis paquetes
package com.agustinestrella;
import java.util.ArrayList;
import java.util.Scanner;

//-----------------------------
import com.clases.bateria;
import com.clases.guitarra;
import com.clases.instrumento;

public class MainInstrumentos {
    public static void main(String[] args) {
        MainInstrumentos fueraStatic = new MainInstrumentos();

        Scanner sc = new Scanner(System.in);
        ArrayList<instrumento> listaInstrumentos = new ArrayList<>();
        int menuChoice;

        do{
            System.out.println("Ingrese que tipo de usuario es, o si desea salir");
            System.out.println("1: Empleado");
            System.out.println("2: Cliente");
            System.out.println("3: Salir ");
            menuChoice = sc.nextInt();

            switch (menuChoice) {
                case 1:
                    fueraStatic.panelEmpleado(sc, listaInstrumentos);
                    break;
            
                case 2:
                    fueraStatic.panelCliente(sc, listaInstrumentos);
                    break;
            }
            
        } while (menuChoice < 1 || menuChoice > 2);


    }

        private void panelCliente(Scanner sc, ArrayList<instrumento> listaInstrumentos){
           int opcion;
           do{
                System.out.println("Seleccione una opcion del menu");
                System.out.println("1: Ver stock");
                System.out.println("2: Comprar un producto");
                System.out.println("3: Salir");
                opcion = sc.nextInt();
           

                switch (opcion) {
                    case 1:
                        if (listaInstrumentos.isEmpty()) {
                            System.out.println("No hay instrumentos en stock");
                            break;
                        }
                        mostrarStock(listaInstrumentos);

                        break;
                
                    case 2:
                        int buyerChoice;
                        mostrarStock(listaInstrumentos);
                        do{
                        System.out.println("Seleccione el numero indexado al instrumoento que desea comprar");
                        buyerChoice = sc.nextInt();
                        } while (buyerChoice > listaInstrumentos.size()-1 || buyerChoice < 0);
                        
                        if (listaInstrumentos.get(buyerChoice).getStock()>0) {
                            System.out.println("Stock disponible, gracias por su compra!");
                            listaInstrumentos.get(buyerChoice).disminuirStock();
                            if (listaInstrumentos.get(buyerChoice).getStock()==0) {
                                listaInstrumentos.remove(buyerChoice);
                            }
                        } else {
                                System.out.println("No hay stock disponible");
                        } 
                    
                        break;
                }

           } while (opcion != 3);
 
        }

        private void panelEmpleado(Scanner sc,  ArrayList<instrumento> listaInstrumentos){
        int employeeChoice;
        do{
            System.out.println("Ingrese una opción del menu");
            System.out.println("1: Agregar instrumento a catálogo");
            System.out.println("2: Ver stock");
            System.out.println("3: Eliminar del stock");
            System.out.println("4: Salir");            
            employeeChoice = sc.nextInt();

            switch (employeeChoice) {
                case 1:
                    int choiceInst;
                    do{
                        System.out.println("Ingrese que producto desea ingresar");
                        System.out.println("1: Guitarra");
                        System.out.println("2: Bateria");
                        choiceInst = sc.nextInt();
                    } while (choiceInst < 1 || choiceInst > 2); 
                    int stock;
                    do{
                        System.out.println("Ingrese la cantidad a ingresar");
                        stock = sc.nextInt();
                    } while (stock <= 0); 

                    
                    switch (choiceInst) {
                        case 1:
                            ingresoGuitarra(sc, listaInstrumentos);
                            break;

                        case 2:
                            ingresoBateria(sc, listaInstrumentos);
                            break;
                        }
                    break;
                
                case 2:
                        mostrarStock(listaInstrumentos);
                        
                        break;
                    
                case 3:
                        mostrarStock(listaInstrumentos);
    
                        System.out.println("Ingrese el numero indexado al producto que desea remover 1 unidad");
                        int seleccion;
                        do {
                        seleccion = sc.nextInt();
                        } while (seleccion >= listaInstrumentos.size() || seleccion < 0);

                        listaInstrumentos.get(seleccion).disminuirStock();

                        if (listaInstrumentos.get(seleccion).getStock()==0){
                            listaInstrumentos.remove(seleccion);
                        }

                        break;  
                }
            } while (employeeChoice != 4);
        }

        public void ingresoGuitarra(Scanner sc, ArrayList<instrumento> listaInstrumentos){
            int choiceBrand;
            String brand;
            do{
                System.out.println("Ingrese la marca de la guitarra");
                System.out.println("1: Shecter");
                System.out.println("2: Ibanez");
                System.out.println("3: Jackson");
                choiceBrand = sc.nextInt();
                sc.nextLine();
            } while (choiceBrand < 1 || choiceBrand > 3);

            if (choiceBrand == 1){
                 brand = "Shecter";
            } else if (choiceBrand == 2){
                brand = "Ibanez";
            } else {
                brand = "Jackson";
            }     
            
            String modelo;
            do{
                System.out.println("Ingrese el modelo de la guitarra de entre 4 y 10 caracteres");
                modelo = sc.nextLine();
            } while (modelo.length() < 4 || modelo.length() > 10 );
            
            int cuerdas;
            do{
                System.out.println("Ingrese la cantidad de cuerdas del instrumento, entre 6 y 9");
                cuerdas = sc.nextInt();
            } while (cuerdas < 6 || cuerdas > 9);

            int stock;
            do{
                System.out.println("Ingrese la cantidad de unidades a ingresar");
                stock = sc.nextInt();
            } while (stock < 1);
            
            double precio;
            do{
            System.out.println("Ingrese el precio del instrumento");
            precio = sc.nextDouble();
            } while (precio < 1);

            instrumento guitarra = new guitarra(cuerdas, stock, precio, brand, modelo);
            listaInstrumentos.add(guitarra);
        }
        public void ingresoBateria(Scanner sc, ArrayList<instrumento> listaInstrumentos){
            int choiceBrand;
            String brand;
            do{
                System.out.println("Ingrese la marca de la bateria");
                System.out.println("1: Yamaha");
                System.out.println("2: DW");
                System.out.println("3: Sonor");
                choiceBrand = sc.nextInt();
                sc.nextLine();
            } while (choiceBrand < 1 || choiceBrand > 3);

            if (choiceBrand == 1){
                 brand = "Yamaha";
            } else if (choiceBrand == 2){
                brand = "DW";
            } else {
                brand = "Sonor";
            }     
            
            String modelo;
            do{
                System.out.println("Ingrese el modelo de la bateria de entre 4 y 10 caracteres");
                modelo = sc.nextLine();
            } while (modelo.length() < 4 || modelo.length() > 10 );
            
            int cuerpos;
            do{
                System.out.println("Ingrese la cantidad de cuerpos del instrumento, entre 4 y 15");
                cuerpos = sc.nextInt();
            } while (cuerpos < 4 || cuerpos > 15);

            int stock;
            do{
                System.out.println("Ingrese la cantidad de unidades a ingresar");
                stock = sc.nextInt();
            } while (stock < 1);
            
            double precio;
            do{
            System.out.println("Ingrese el precio del instrumento");
            precio = sc.nextDouble();
            } while (precio < 1);

            instrumento bateria = new bateria(cuerpos, precio, brand, modelo, stock);
            listaInstrumentos.add(bateria);
        }
        public void mostrarStock(ArrayList<instrumento> listaInstrumentos){
            for (int i=0; i < listaInstrumentos.size(); i++){
                if (listaInstrumentos.get(i) instanceof guitarra) {
                    System.out.println(i+ ": |Guitarra " +listaInstrumentos.get(i).getMarca()+ "," +listaInstrumentos.get(i).getModelo()+ "," +listaInstrumentos.get(i).getStock());           
                } else if (listaInstrumentos.get(i) instanceof bateria) {
                    System.out.println(i+ ": |Batería " +listaInstrumentos.get(i).getMarca()+ "| Modelo: " +listaInstrumentos.get(i).getModelo()+ "| Stock:" +listaInstrumentos.get(i).getStock());
                }
            }
        }
    }