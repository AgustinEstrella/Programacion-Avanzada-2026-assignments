package com.mycompany.solidplataformaeduvirtual.Evaluaciones;
// Tipo de evaluación: Examen
public class Examen implements Evaluacion{
    @Override
    public double calcularNotaFinal(double nota) {
        return nota;
    }
}
