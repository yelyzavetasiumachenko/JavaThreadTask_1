package Task1;

public class Main {
    public static void main(String[] args) {
        int numberOfThreads = 25;  // Змінна кількість потоків
        int step = 10;             // Крок послідовності
        int delayMs = 2000;       // Затримка 2 секунди між зупинкою потоків

        // Ініціалізуємо керуючий потік, передаємо кількість робочих потоків та затримку
        BreakThread breakThread = new BreakThread(numberOfThreads, delayMs);

        // Запускаємо робочі потоки
        for (int i = 1; i <= numberOfThreads; i++) {
            new MainThread(i, step, breakThread).start();
        }

        // Запускаємо керуючий потік
        new Thread(breakThread).start();
    }
}
