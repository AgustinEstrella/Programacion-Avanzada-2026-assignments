package clases;

import java.util.List;

public class Mago extends PersonajeBase {

    private int mana;
    private String elemento;

    //Pendiente incluir uso de enums para seleccion de elemento

    public Mago (String nombre, int vida, List<String> habilidades, int mana, String elemento){
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
    
}
