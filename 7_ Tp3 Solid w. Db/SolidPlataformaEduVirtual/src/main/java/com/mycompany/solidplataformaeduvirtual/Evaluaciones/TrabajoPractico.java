package com.mycompany.solidplataformaeduvirtual.Evaluaciones;
// Tipo de evaluación: Trabajo Práctico
public class TrabajoPractico implements Evaluacion{
     @Override
    public double calcularNotaFinal(double nota) {
      // Se descuenta un 10%
        return nota * 0.90;
    }
}
