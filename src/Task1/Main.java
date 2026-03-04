package Task1;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int numberOfThreads = 8;  // Змінна кількість потоків
        int step = 10;            // Крок послідовності

        // Масив для зберігання випадкового часу роботи для кожного потоку
        int[] stopTimesMs = new int[numberOfThreads];
        Random random = new Random();

        System.out.println("--- ІНІЦІАЛІЗАЦІЯ ПОТОКІВ ---");

        // Ініціалізуємо керуючий потік, передаючи масив із запланованим часом
        BreakThread breakThread = new BreakThread(numberOfThreads, stopTimesMs);

        // Генеруємо затримки і запускаємо робочі потоки
        for (int i = 1; i <= numberOfThreads; i++) {
            // Генеруємо випадковий час для потоку від 1000 до 10000 мілісекунд (1-10 сек)
            stopTimesMs[i - 1] = 1000 + random.nextInt(9001);

            System.out.printf("Запуск потоку %d. Запланований час роботи: %.1f с.\n",
                    i, stopTimesMs[i - 1] / 1000.0);

            new MainThread(i, step, breakThread).start();
        }

        // Запускаємо керуючий потік
        new Thread(breakThread).start();
    }
}

































//package Task1;
//
//public class Main {
//    public static void main(String[] args) {
//        int numberOfThreads = 25;  // Змінна кількість потоків
//        int step = 10;             // Крок послідовності
//        int delayMs = 2000;       // Затримка 2 секунди між зупинкою потоків
//
//        // Ініціалізуємо керуючий потік, передаємо кількість робочих потоків та затримку
//        BreakThread breakThread = new BreakThread(numberOfThreads, delayMs);
//
//        // Запускаємо робочі потоки
//        for (int i = 1; i <= numberOfThreads; i++) {
//            new MainThread(i, step, breakThread).start();
//        }
//
//        // Запускаємо керуючий потік
//        new Thread(breakThread).start();
//    }
//}
