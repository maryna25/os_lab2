public class RunnableWithDekkersLock implements Runnable {
    public static int counter = 0;
    private DekkersLock lock;


    public RunnableWithDekkersLock(DekkersLock lock) {
//        System.out.println("Creating new thread...");
        this.lock = lock;
    }

    @Override
    public void run() {
        lock.register();

        for (int i = 0; i < 1000000; i++) {

            lock.lock();
//            System.out.println("Locked thread: " + Thread.currentThread().getName());

            f();

//            System.out.println("Unlocked thread: " + Thread.currentThread().getName());
            lock.unlock();
        }

        lock.unregister();
    }

    private void f() {
        counter++;
    }
}
