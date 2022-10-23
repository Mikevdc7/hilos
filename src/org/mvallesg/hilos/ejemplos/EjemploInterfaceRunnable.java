package org.mvallesg.hilos.ejemplos;

import org.mvallesg.hilos.ejemplos.runnable.ViajeTarea;

public class EjemploInterfaceRunnable {
    public static void main(String[] args) {

        new Thread(new ViajeTarea("Vilar de Canes")).start();
        new Thread(new ViajeTarea("La Torre Invasora")).start();
        new Thread(new ViajeTarea("Albocarcel")).start();
        new Thread(new ViajeTarea("Benasucre")).start();
    }
}
