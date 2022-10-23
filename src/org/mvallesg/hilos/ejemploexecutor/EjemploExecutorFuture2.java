package org.mvallesg.hilos.ejemploexecutor;

import java.awt.*;
import java.util.concurrent.*;

public class EjemploExecutorFuture2 {
    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {

//        ExecutorService executor = Executors.newFixedThreadPool(2);
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(3);

        System.out.println("Tamaño del pool: " + executor.getPoolSize());
        System.out.println("Cantidad de tareas en cola: " + executor.getQueue().size());

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

        Callable<Integer> tarea2 = () ->{
            System.out.println("Iniciando tarea 3...");
            TimeUnit.SECONDS.sleep(10);
            return 10;
        };

        Future<String> resultado = executor.submit(tarea);
        Future<String> resultado2 = executor.submit(tarea);
        Future<Integer> resultado3 = executor.submit(tarea2);

        System.out.println("Tamaño del pool: " + executor.getPoolSize());
        System.out.println("Cantidad de tareas en cola: " + executor.getQueue().size());

        executor.shutdown();
        System.out.println("Continuando con la ejecución del método main");

        while(!(resultado.isDone() && resultado2.isDone() && resultado3.isDone())){
            System.out.println(String.format("resultado1: %s - resultado2: %s - resultado3: %s",
                    resultado.isDone()?"Finalizo":"En proceso",
                    resultado2.isDone()?"Finalizo":"En proceso",
                    resultado3.isDone()?"Finalizo":"En proceso"));
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println("Obtenemos el resultado de la tarea: " + resultado.get());
        System.out.println(resultado.isDone());

        System.out.println("Obtenemos el resultado2 de la tarea: " + resultado.get());
        System.out.println(resultado2.isDone());

        System.out.println("Obtenemos el resultado3 de la tarea 3: " + resultado.get());
        System.out.println(resultado3.isDone());

        /* el get() bloquea el main hasta que obtiene el resultado,
        por lo que en algunos casos hay que ir con cuidado, y no usarlo
        siempre.
        Si la tarea es Runnable, no devuelve nada, por lo que el get devolverá un null.
        Si la tarea es Callable, tendrá un return, y el get devolverá ese resultado.
        Esa es la diferencia entre una tarea Runnable y una Callable, por lo demás, son iguales.
         */

    }
}