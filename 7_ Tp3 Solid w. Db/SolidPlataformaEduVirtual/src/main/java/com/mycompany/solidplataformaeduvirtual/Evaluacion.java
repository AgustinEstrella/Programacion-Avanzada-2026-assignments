package com.mycompany.solidplataformaeduvirtual;
// Interfaz que demuestra el principio OCP
public interface Evaluacion {
    // Cada evaluación calcula la nota a su manera
    double calcularNotaFinal(double nota);
}
