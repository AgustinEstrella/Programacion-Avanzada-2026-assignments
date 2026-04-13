package com.clases;

public class bateria extends instrumento{

    private int cuerpos;

    public bateria (int cuerpos, double precio, String marca, String modelo, int stock){
        super(precio, marca, modelo, stock);
        this.cuerpos = cuerpos;
        if (cuerpos < 1) {
            throw new IllegalArgumentException("La bateria debe tener al menos 1 cuerpo");
        }
    }

    public int getCuerpos(){
        return cuerpos;
    }
}