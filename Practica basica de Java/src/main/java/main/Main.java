package main;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int cantNum;
        double acumNums=0;
        double avgNums; 

        Random numsRand = new Random();

        System.out.println("Agustin Estrella - Legajo: 114539");
        System.out.println("---------------------------------");

        do{
        //Pido ingreso por parte del usuario para testeo y control de funcionamiento
            System.out.println("Ingrese la cantidad de numeros a ingresar");
            cantNum = sc.nextInt();
        } while (cantNum < 0);
        int numeros[] = new int[cantNum];

        int generado;
        for (int i=0; i<cantNum; i++){
           generado = numsRand.nextInt((1000-10)+1)+10;
            numeros[i] = generado;
        //Imprimo los numeros para verificar que ingresen correctamente
           System.out.println("Nro: " +i+ ": " +numeros[i]);
           acumNums = acumNums + numeros[i];
        }

        System.out.println("Suma de los numeros: " +acumNums);

        avgNums = acumNums/cantNum;
        System.out.println("Promedio de los numeros: " + avgNums);
    }
}