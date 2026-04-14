import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import clases.*;
import enums.*;

public class MainPrototipe {
    public static void main(String[] args){
        MainPrototipe metodos = new MainPrototipe();

        Scanner sc = new Scanner(System.in);
        
        int seleccion;
        int cant;
        do{
            System.out.println("Bienvenido a esta prueba de prototype");
            System.out.println("Ingrese que tipo de personaje quiere generar, se creara 1 original y el resto seran copias");
            System.out.println("1: Magos");
            System.out.println("2: Barbaros");
            seleccion = sc.nextInt();
        } while (seleccion > 2 || seleccion < 1);

        do{
            System.out.println("Ingrese la cantidad a generar");
            cant = sc.nextInt();
        } while (cant < 1);
            
        int decision;

            switch (seleccion) {
                case 1:
                    PersonajeBase magoBase = metodos.creacionMago(sc);
                    List<PersonajeBase> clonesGenerados = metodos.generarClones(magoBase, cant);
                    metodos.mostrarEjercito(sc, clonesGenerados);
                    break;
                
                case 2:
                    PersonajeBase barbaroBase = metodos.creacionBarbaro(sc);
                    List<PersonajeBase> clonesGenerados2 = metodos.generarClones(barbaroBase, cant);
                    metodos.mostrarEjercito(sc, clonesGenerados2);
                    break;  
            }
        
    }

    public Barbaro creacionBarbaro(Scanner sc){

        //Ingreso de vida
        int vida;
        do{
            System.out.println("Ingrese la vida del barbaro");
            vida = sc.nextInt();
        } while (vida < 1);
    
        //Ingreso de habilidades
        int contHab;
        do{
            System.out.println("Ingrese la cantidad de habilidades a agregar, con un máximo de 6");
            contHab = sc.nextInt();
        } while (contHab < 0 || contHab > 6);

        List<Habilidades> habilidadesSeleccionadas = ingresoHabilidades(sc, contHab);
        
        //Ingreso de audacia
        int audacia;
        do{
            System.out.println("Ingrese la audacia del barbaro, entre 0 y 1000");
            audacia = sc.nextInt();
        } while (audacia < 0 || audacia > 1000);
            
        //Ingreso de fuerza
         int fuerza;
        do{
            System.out.println("Ingrese la fuerza del barbaro, entre 0 y 10000");
            fuerza = sc.nextInt();
        } while (fuerza < 0 || fuerza > 10000);

         Barbaro barbaro = new Barbaro("Barbarin", vida, habilidadesSeleccionadas, fuerza, audacia);
         
         System.out.println("Ingresado al sistema: " +barbaro.toString());
         return barbaro;
    }
    
    public Mago creacionMago(Scanner sc){
        //Ingreso de vida
        int vida;
        do{
            System.out.println("Ingrese la vida del mago");
            vida = sc.nextInt();
        } while (vida < 1);
    
        //Ingreso de habilidades
        int contHab;
        do{
            System.out.println("Ingrese la cantidad de habilidades a agregar, con un máximo de 6");
            contHab = sc.nextInt();
        } while (contHab < 0 || contHab > 6);

        List<Habilidades> habilidadesSeleccionadas = ingresoHabilidades(sc, contHab);
        
        //Ingreso de mana
        int mana;
        do{
            System.out.println("Ingrese el mana del mago, entre 10 y 500");
            mana = sc.nextInt();
        } while (mana < 10 || mana > 500);
            
        //Ingreso elemento
         Elementos elementoSeleccionado = seleccionElemento(sc);

         Mago mago = new Mago("Maguin", vida, habilidadesSeleccionadas, mana, elementoSeleccionado);
        
         System.out.println("Ingresado al sistema: " +mago.toString());

         return mago;
    }

    public List<Habilidades> ingresoHabilidades(Scanner sc, int cantHabilidades){
        //Convierto mi enum en un array para solicitar ingreso de usuario
        Habilidades[] opciones = Habilidades.values();
        //-------------------------------------------------------------//

        //Inicializo mi List como arraylist para darle metodos como .add
        //Le permito como campos los elementos de mi enum
        List<Habilidades> habilidadesElegidas = new ArrayList<Habilidades>();
        //-----------------------------------------------------------------//

        int seleccionHabilidad;

        //Muestro por pantalla las opciones
        for (int i=0; i < opciones.length; i++){
            System.out.println((i+1) + ": " + opciones[i]);
        }
        //-----------------------------------------------//

        //Itero tantas veces como habilidades se quieran agregar
        for (int i=0; i < cantHabilidades; i++)
        {
            do{
                System.out.print("Tu elección: ");
                seleccionHabilidad = sc.nextInt();
            } while (seleccionHabilidad > opciones.length || seleccionHabilidad < 1);  

            //Asigno la variable habilidadSeleccionada como Habilidades para guardar el valor que esta en el enum hecho array
            Habilidades habilidadSeleccionada = opciones[seleccionHabilidad-1];

            //Agrego esta opcion del Enum a mi List -> List<Habilidades>
            habilidadesElegidas.add(habilidadSeleccionada);
        }

        //Retorno la lista completa
        return habilidadesElegidas;
    }

    public Elementos seleccionElemento(Scanner sc){
        //El mismo proceso que en la linea 72
        Elementos[] opciones = Elementos.values();
        //-------------------------------------//

        int seleccionElemento;
        for (int i=0; i < opciones.length; i++){
            System.out.println((i+1) +": " +opciones[i]);
        }
        do{
            System.out.print("Tu elección: ");
            seleccionElemento = sc.nextInt();
        } while (seleccionElemento > opciones.length || seleccionElemento < 1); 
        
        Elementos elegido = opciones[seleccionElemento-1];

        return elegido;
    }

    public void mostrarEjercito(Scanner sc, List<PersonajeBase> listaClones){
        int decision;
        do{
            System.out.println("Desea ver el ejercito completo?");
            System.out.println("1: Si");
            System.out.println("2: No");
            decision = sc.nextInt();
        } while (decision != 1 && decision != 2);

        if (decision == 1) {
            for (PersonajeBase x : listaClones){ //Significa: Para cada objeto x del tipo PersonajeBase en listaClones
                System.out.println(x.toString());
            }
        } else {
            System.out.println("Volviendo...");
        }
    }

    public List<PersonajeBase> generarClones(PersonajeBase Prototipo, int cantCopias){

        List<PersonajeBase> clonesGenerados = new ArrayList<>();

        if (cantCopias > 0) {
            for (int i=0; i < cantCopias; i++) {
                PersonajeBase elClon = Prototipo.clonar();
                clonesGenerados.add(elClon);
            }
            System.out.println("El ejercito de " +cantCopias +" fue generado!");
        } else {
            System.out.println("¡ERROR! La cantidad de copias debe ser mayor a 0");
        }

        return clonesGenerados;
    }
}