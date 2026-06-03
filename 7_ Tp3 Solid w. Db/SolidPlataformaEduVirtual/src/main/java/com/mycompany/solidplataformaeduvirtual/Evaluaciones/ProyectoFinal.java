package com.mycompany.solidplataformaeduvirtual.Evaluaciones;
// Tipo de evaluación: Proyecto Final
public class ProyectoFinal implements Evaluacion {
    //Promedio normal más 1 punto extra
     @Override
    public double calcularNotaFinal(double [] notas) {
        double suma = 0;

        for(double nota : notas) {
            suma += nota;
        }

        double promedio = suma / notas.length;

        return promedio + 1;
    }
        
}
