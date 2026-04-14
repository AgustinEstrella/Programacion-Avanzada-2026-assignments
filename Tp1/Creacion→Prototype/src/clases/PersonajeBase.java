 package clases;

import interfaces.Prototipo;

import java.util.List ;

import enums.Habilidades;

import java.util.Collections;
//explico el uso de Collections en linea 46

public class PersonajeBase implements Prototipo<PersonajeBase> {

    private String nombre;
    private int vida;
    private List<Habilidades> habilidades;

    public PersonajeBase(String nombre, int vida, List<Habilidades> habilidades){
        this.nombre = nombre;
        this.vida = vida;
        this.habilidades = habilidades;
    }

    public PersonajeBase(PersonajeBase Prototipo){
        this.nombre = nombre;
        this.vida = vida;
        this.habilidades = habilidades;
    }

     @Override
     public PersonajeBase clonar(){
        return new PersonajeBase(this);
     }

     public String getNombre(){
        return nombre;
     }

     public int getVida(){
        return vida;
     }
     public void setVida(int vida){
        System.out.println("Ingrese la vida del personaje, valor entre 1 y 100");
        do{
            this.vida = vida;
        } while (vida < 1 || vida > 100);
     }

     public List<Habilidades> getHabilidades(){
        //uso collections para poder mostrar el List como una estructura inmodificable
        //para evitar llamados como getHabilidades.add(""), lo cual rompe integridad y seguridad
        return Collections.unmodifiableList(habilidades);
     }

}