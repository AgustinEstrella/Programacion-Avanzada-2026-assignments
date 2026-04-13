package com.clases;

public class instrumento {

    private double precio;
    private String marca;
    private String modelo;
    private int stock;

    public instrumento(double precio, String marca, String modelo, int stock){
        this.precio = precio;
        if (precio <= 0) {
            throw new IllegalArgumentException ("Precio invalido");            
        }
        this.marca = marca;
        this.modelo = modelo;   
    }

    public double getPrecio(double precio){
        return precio;
    }
    public void setPrecio(double precio){
        do {
            System.out.println("Ingrese el nuevo precio del producto");
            this.precio = precio;
            if (precio <= 0) {
                System.out.println("El precio no puede ser nulo o negativo");            
            }
        } while (precio > 0);        
    }
    
    public String getMarca(){
        return marca;
    }

    public String getModelo(){
        return modelo;
    }

    public int getStock(){
        return stock;
    }

    public void setStock(int stock){
        do{
            this.stock = stock;
            if (stock < 1) {
                System.out.println("Las existencias no pueden ser nulas o negativas");
            }
        } while (stock < 1);
    }
    public void disminuirStock(){
        if (stock > 0) {
         stock = stock--;
        } else if (stock == 0){
            System.out.println("Sin stock");
        }
    }
    
}