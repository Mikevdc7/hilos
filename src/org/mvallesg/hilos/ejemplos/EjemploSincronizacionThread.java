package org.mvallesg.hilos.ejemplos;

import org.mvallesg.hilos.ejemplos.runnable.ImprimirFrases;

public class EjemploSincronizacionThread {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new ImprimirFrases("Hola ", "k Ase?"));
        t1.start();
        Thread t2 = new Thread(new ImprimirFrases("Cuéntame ", "lo k ase"));
        t2.start();
        Thread.sleep(100);
        Thread t3 = new Thread(new ImprimirFrases("Hago dinero ", "ile gal"));
        t3.start();
        Thread.sleep(100);
        System.out.println(t3.getState());
        Thread t4 = new Thread(new ImprimirFrases("Quiero mansion ", "en Sene gal"));
        t4.start();


    }

    public synchronized static void imprimirFrases(String frase1, String frase2){
        System.out.print(frase1);

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(frase2);
    }
}