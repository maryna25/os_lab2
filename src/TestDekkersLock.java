import java.util.ArrayList;

public class TestDekkersLock {

    public static void main(String[] args) {
        test(new RunnableWithoutLock());
//        test(new RunnableWithDekkersLock(new DekkersLock()));
    }

    private static void test(Runnable runnable) {
        int numOfThreads = 2;
        ArrayList<Thread> threadsList = new ArrayList<>();

        for (int i = 0;  i < numOfThreads; i++) {
            threadsList.add(new Thread(runnable));
        }

        for(Thread thread: threadsList) {
            thread.start();
        }

        System.out.println(("Counter: " + RunnableWithoutLock.counter));
    }

}
