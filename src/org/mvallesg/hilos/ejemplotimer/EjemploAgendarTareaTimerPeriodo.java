package org.mvallesg.hilos.ejemplotimer;

import java.awt.*;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

public class EjemploAgendarTareaTimerPeriodo {
    public static void main(String[] args) {

        Toolkit toolKit = Toolkit.getDefaultToolkit();
        AtomicInteger contadorAtomic = new AtomicInteger(3);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                int i = contadorAtomic.getAndDecrement();
                if(i>0){
                    toolKit.beep();
                    System.out.println("Tarea " + i + " periódica en: " + new Date() + " nombre del Thread: "
                    + Thread.currentThread().getName());
                } else{
                    System.out.println("Finaliza el tiempo");
                    timer.cancel();
                }
            }
        }, 0, 2000);

        System.out.println("Agendamos una tarea inmediata que se repite cada 2 segundos ...");
    }
}

