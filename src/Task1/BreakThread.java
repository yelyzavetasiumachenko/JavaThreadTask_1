package Task1;

public class BreakThread implements Runnable {
    // Масив прапорців для зупинки кожного потоку окремо
    private final boolean[] canBreak;
    private final int[] stopTimesMs;
    private final int threadCount;

    public BreakThread(int threadCount, int[] stopTimesMs) {
        this.threadCount = threadCount;
        this.canBreak = new boolean[threadCount];
        this.stopTimesMs = stopTimesMs;
    }

    @Override
    public void run() {
        // Фіксуємо час старту (точний секундомір)
        long startTime = System.currentTimeMillis();
        int stoppedCount = 0; // Лічильник зупинених потоків

        // Керуючий потік працює, поки не зупинить усі робочі потоки
        while (stoppedCount < threadCount) {
            long elapsed = System.currentTimeMillis() - startTime;

            for (int i = 0; i < canBreak.length; i++) {
                // Якщо потік ще працює І час його роботи вже вийшов
                if (!canBreak[i] && elapsed >= stopTimesMs[i]) {
                    synchronized (this) {
                        canBreak[i] = true; // Даємо дозвіл на зупинку
                    }
                    stoppedCount++; // Фіксуємо, що зупинили ще один потік
                }
            }
        }
    }

    // Перевірка дозволу для конкретного потоку за його індексом
    synchronized public boolean isCanBreak(int threadIndex) {
        return canBreak[threadIndex];
    }
}



































//package Task1;
//
//public class BreakThread implements Runnable {
//    // Масив прапорців для зупинки кожного потоку окремо
//    private final boolean[] canBreak;
//    private final int delayMs;
//
//    public BreakThread(int threadCount, int delayMs) {
//        this.canBreak = new boolean[threadCount];
//        this.delayMs = delayMs;
//    }
//
//    @Override
//    public void run() {
//        for (int i = 0; i < canBreak.length; i++) {
//            try {
//                // Заданий проміжок часу перед зупинкою наступного потоку
//                Thread.sleep(delayMs);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            // Генеруємо дозвіл для конкретного потоку
//            synchronized (this) {
//                canBreak[i] = true;
//            }
//        }
//    }
//
//    // Перевірка дозволу для конкретного потоку за його індексом
//    synchronized public boolean isCanBreak(int threadIndex) {
//        return canBreak[threadIndex];
//    }
//}
//
//
