import java.util.ArrayList;

public class TestDekkersLock {

    public static void main(String[] args) {
//       test(new RunnableWithoutLock());
        test(new RunnableWithDekkersLock(new DekkersLock()));
    }

    private static void test(Runnable runnable) {
        int numOfThreads = 2;
        ArrayList<Thread> threads = new ArrayList<>();

        for (int i = 0;  i < numOfThreads; i++) {
            threads.add(new Thread(runnable));
        }

        for(Thread thread: threads) {
            thread.start();
        }

        for(Thread thread: threads) {
            try {
                thread.join();
            } catch (InterruptedException x) {
                x.printStackTrace();
            }
        }

        System.out.println("----------------------------------------------------");
        System.out.println(("Counter: " + RunnableWithDekkersLock.counter));
        System.out.println(("Counter: " + RunnableWithoutLock.counter));
        System.out.println("----------------------------------------------------");
    }

}
