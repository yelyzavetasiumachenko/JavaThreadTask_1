package Task1;

import java.math.BigInteger;

public class MainThread extends Thread {
    private final int id;
    private final int step; // Крок зростання послідовності
    private final BreakThread breakThread;

    public MainThread(int id, int step, BreakThread breakThread) {
        this.id = id;
        this.step = step;
        this.breakThread = breakThread;
    }

    @Override
    public void run() {
        BigInteger sum = BigInteger.ZERO;
        long currentTerm = 0;
        long count = 0;
        boolean isStop = false;

        do {
            // Додаємо поточний елемент до загальної суми
            sum = sum.add(BigInteger.valueOf(currentTerm));
            currentTerm += step; // Збільшуємо на заданий крок
            count++;

            // Перевіряємо, чи надійшов сигнал на зупинку саме для цього потоку
            // Оскільки id починається з 1, індекс у масиві буде (id - 1)
            isStop = breakThread.isCanBreak(id - 1);
        } while (!isStop);

        System.out.println("Потік " + id + " | Сума: " + sum + " | Доданків: " + count);
    }
}
