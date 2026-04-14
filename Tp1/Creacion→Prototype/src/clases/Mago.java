package clases;

import enums.Elementos;
import enums.Habilidades;

import java.util.List;

public class Mago extends PersonajeBase {

    private int mana;
    private Elementos elemento;

    public Mago (String nombre, int vida, List<Habilidades> habilidades, int mana, Elementos elemento){
        super(nombre, vida, habilidades);
        this.mana = mana;
        this.elemento = elemento;
    }

    public Mago (Mago Prototipo){
        super(Prototipo);
        this.mana = mana;
        this.elemento = elemento;
    }

    @Override
    public Mago clonar(){
        return new Mago(this);
    }

    @Override
    public String toString(){
        return super.toString() + "Mana: " +mana + " Elemento: " +elemento;
    }
    
}
