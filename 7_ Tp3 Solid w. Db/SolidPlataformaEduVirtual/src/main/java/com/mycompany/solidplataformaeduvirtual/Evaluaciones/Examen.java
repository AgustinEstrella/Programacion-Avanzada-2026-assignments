package com.mycompany.solidplataformaeduvirtual.Evaluaciones;
// Tipo de evaluación: Examen
public class Examen implements Evaluacion{
    //promedio normal
    @Override
    public double calcularNotaFinal(double [] notas) {
        double suma = 0;

        for(double nota : notas) {
            suma += nota;
        }

        return suma / notas.length;
    }
    
}
