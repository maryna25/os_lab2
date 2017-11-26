public class BakeryLockTestThread implements Runnable {
    Thread thread;
    static BakeryLock BLock = new BakeryLock();

    static int generalCounter = 0;
    static int lockedThreadId;

    int numberOfOperations = 0;
    int id;
    char operation;


    BakeryLockTestThread(char oper) {
        thread = new Thread(this, "BackeryLockThread");
        operation = oper;
        thread.start();
    }

    public void run() {
        id= BLock.getId();
        // BLock.lock();
        if (operation == '+') inc();
        else dec();
        // BLock.unlock();
    }

    public void inc() {
        while (true) {
            BLock.lock();
            lockedThreadId = id;
            generalCounter++;
            numberOfOperations++;
            if (lockedThreadId != id) System.out.println("\n\n\n---more than one thread is in critical section---\n\n\n");
            BLock.unlock();
        }
    }

    public void dec() {
        while (true) {
            BLock.lock();
            lockedThreadId = id;
            generalCounter--;
            numberOfOperations++;
            if (lockedThreadId != id) System.out.println("\n\n\n---more than one thread is in critical section---\n\n\n");
            BLock.unlock();
        }
    }

    static void getStaticCounter() {
        System.out.println("Static counter = " + generalCounter);
    }

    public void getOperationsNumber() {
        System.out.println(id + " thread with operation " + operation + ": Number of operations = " + numberOfOperations);
    }
}