package Task1;

public class BreakThread implements Runnable {
    // Масив прапорців для зупинки кожного потоку окремо
    private final boolean[] canBreak;
    private final int delayMs;

    public BreakThread(int threadCount, int delayMs) {
        this.canBreak = new boolean[threadCount];
        this.delayMs = delayMs;
    }

    @Override
    public void run() {
        for (int i = 0; i < canBreak.length; i++) {
            try {
                // Заданий проміжок часу перед зупинкою наступного потоку
                Thread.sleep(delayMs);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Генеруємо дозвіл для конкретного потоку
            synchronized (this) {
                canBreak[i] = true;
            }
        }
    }

    // Перевірка дозволу для конкретного потоку за його індексом
    synchronized public boolean isCanBreak(int threadIndex) {
        return canBreak[threadIndex];
    }
}


