public class RunnableWithoutLock implements Runnable {
    public static int counter = 0;

    public RunnableWithoutLock() {
        System.out.println("Creating new thread...");
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000000; i++) {
            f();
        }
    }

    private void f() {
        counter++;
    }
}
