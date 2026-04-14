package clases;

import java.util.List;

import enums.Habilidades;

public class Barbaro extends PersonajeBase{
    
    private int fuerza;
    private int audacia;

    public Barbaro(String nombre, int vida, List<Habilidades> habilidades, int fuerza, int audacia){
        super(nombre, vida, habilidades);
        this.fuerza = fuerza;
        this.audacia = audacia;
    }

    public Barbaro(Barbaro Prototipo){
        super(Prototipo);
        this.fuerza = fuerza;
        this.audacia = audacia;
    }

    @Override
    public Barbaro clonar(){
        return new Barbaro(this);
    }

    @Override
    public String toString(){
        return super.toString() + ". Fuerza: " +fuerza +". Audacia: " +audacia;
    }

}
