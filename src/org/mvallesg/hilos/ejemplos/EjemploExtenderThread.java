package org.mvallesg.hilos.ejemplos;

import org.mvallesg.hilos.ejemplos.threads.NombreThread;

public class EjemploExtenderThread {
    public static void main(String[] args) throws InterruptedException {
        Thread hilo = new NombreThread("Mike");
        hilo.start();
        // Esto es una pausa en el método main,
        // no en el hilo que acabamos de crear
        // Thread.sleep(1);
        Thread hilo2 = new NombreThread("Andrea");
        hilo2.start();

        Thread hilo3 = new NombreThread("Nerea");
        hilo3.start();
        System.out.println(hilo.getState());
    }
}