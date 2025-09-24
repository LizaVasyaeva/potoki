public class Main {
    public static void main(String[] args) {
        System.out.println("Главный поток запущен");


        Thread[] threads = new Thread[10];


        for (int i = 0; i < 10; i++) {
            final int threadNumber = i + 1;
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("Поток " + threadNumber + " запущен");

                    try {
                        for (int j = 5; j > 0; j--) {
                            System.out.println("Поток " + threadNumber + " считает: " + j);
                            Thread.sleep(1000); // Приостановка на 1 секунду
                        }
                    } catch (InterruptedException e) {
                        System.out.println("Поток " + threadNumber + " прерван");
                    }

                    System.out.println("Поток " + threadNumber + " завершён");
                }
            }, "Поток " + threadNumber);


            threads[i].start();
        }


        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        for (Thread thread : threads) {
            System.out.println(thread);
        }

        System.out.println("Главный поток завершён");


        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            System.out.println("Главный поток прерван при ожидании");
        }
    }
}