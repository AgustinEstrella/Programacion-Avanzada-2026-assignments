package com.mycompany.solidplataformaeduvirtual;
// Tipo de evaluación: Proyecto Final
public class ProyectoFinal implements Evaluacion {
     @Override
    public double calcularNotaFinal(double nota) {
     // Se agrega un 10%
        return nota * 1.10;
    }
}
