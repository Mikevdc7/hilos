package org.mvallesg.hilos.ejemplos;

public class EjemploRunnableJava8 {
    public static void main(String[] args) throws InterruptedException {

        Thread main = Thread.currentThread();

        // Versión con clase anónima:
        /*Runnable viaje = new Runnable() {
            @Override
            public void run() {
                for(int i=0; i<10; i++){
                    System.out.println(i + " - " + Thread.currentThread().getName());
                    try {
                        Thread.sleep((long)(Math.random() * 1000));
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println("Finalmente me voy de viaje a: " + Thread.currentThread().getName());
            }
        };*/

        // Versión con expresiones Lambda:
        Runnable viaje = ()->{
                for(int i=0; i<10; i++){
                    System.out.println(i + " - " + Thread.currentThread().getName());
                    try {
                        Thread.sleep((long)(Math.random() * 1000));
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println("Finalmente me voy de viaje a: " + Thread.currentThread().getName());
                System.out.println(main.getState());
            };

        Thread th1 = new Thread(viaje, "Vilar de Canes");
        Thread th2 = new Thread(viaje, "La Torre Invasora");
        Thread th3 = new Thread(viaje, "Albocarcel");
        Thread th4 = new Thread(viaje, "Benasucre");

        th1.start();
        th2.start();
        th3.start();
        th4.start();
        th1.join();
        th2.join();
        th3.join();
        th4.join();

        //Thread.sleep(8000);

        System.out.println("Continuando con la ejecución del método main: " + main.getName());
    }
}