package com.mycompany.solidplataformaeduvirtual.Evaluaciones;
// Interfaz que demuestra el principio OCP
public interface Evaluacion {
    // Cada evaluación calcula la nota a su manera
    double calcularNotaFinal(double nota);
}
