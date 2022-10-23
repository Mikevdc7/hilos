package org.mvallesg.hilos.ejemploexecutor;

import java.awt.*;
import java.util.concurrent.*;

public class EjemploExecutorFuture {
    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Toolkit toolkit = Toolkit.getDefaultToolkit();

        /* Con Runnable:
        Runnable tarea = () ->{
            System.out.println("Inicio de la tarea...");
            try {
                System.out.println("Nombre del thread: " + Thread.currentThread().getName());
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
            toolkit.beep();
            System.out.println("Finaliza la tarea...");
        };
        Future<?> resultado = executor.submit(tarea);
        executor.shutdown();
        */
        // Con Callable:
        Callable<String> tarea = () ->{
            System.out.println("Inicio de la tarea...");
            try {
                System.out.println("Nombre del thread: " + Thread.currentThread().getName());
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
            toolkit.beep();
            System.out.println("Finaliza la tarea...");
            return "Algún resultado importante de la tarea";
        };
        Future<String> resultado = executor.submit(tarea);
        executor.shutdown();
        System.out.println("Continuando con la ejecución del método main");

        while(!resultado.isDone()){
            System.out.println("Ejecutando tarea...");
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println("Obtenemos el resultado de la tarea: " + resultado.get());
        System.out.println(resultado.isDone());

        /* el get() bloquea el main hasta que obtiene el resultado,
        por lo que en algunos casos hay que ir con cuidado, y no usarlo
        siempre.
        Si la tarea es Runnable, no devuelve nada, por lo que el get devolverá un null.
        Si la tarea es Callable, tendrá un return, y el get devolverá ese resultado.
        Esa es la diferencia entre una tarea Runnable y una Callable, por lo demás, son iguales.
         */

    }
}