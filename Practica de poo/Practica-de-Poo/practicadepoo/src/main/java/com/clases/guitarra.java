package com.clases;

public class guitarra extends instrumento {
    
    private int cantCuerdas;

    public guitarra (int cantCuerdas, int stock, double precio, String marca, String modelo){
        super(precio, marca, modelo, stock);
        this.cantCuerdas = cantCuerdas;
    }

    public int getCantCuerdas(){
        return cantCuerdas;
    }

}
