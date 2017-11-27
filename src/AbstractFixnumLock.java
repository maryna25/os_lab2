import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;

public abstract class AbstractFixnumLock implements FixnumLock {
    private int threadNumber = 20;
    public  int numberOfRegistered = 0;

    ArrayList<Boolean> pidList = getFilledList(threadNumber, false);
    private ThreadLocal<Integer> ID = new ThreadLocal<>();

    private static final Object sync = new Object();

    AbstractFixnumLock() {}
    AbstractFixnumLock(int number) {
        threadNumber = number;
    }

    public <T>ArrayList<T> getFilledList(int size, T value) {
        ArrayList<T> list = new ArrayList<>();
        for(int i = 0; i < size; ++i) {
            list.add(value);
        }
        return list;
    }

    @Override
    public int getId() {
        if (ID.get() != null) return ID.get();
        return register();
    }

    @Override
    public int register() {
        synchronized (sync) {
            if (ID.get() != null) return ID.get();

            System.out.println("Registering...");
            int freeID = findPid();
            if (freeID != -1) {
                pidList.set(findPid(), true);
                ID.set(freeID);
                numberOfRegistered++;
            }

            System.out.println("Register PID = " + freeID);
            return freeID;
        }
    }

    @Override
    public int unregister() {
        synchronized (sync) {
            if (ID.get() != null) {
                int threadID = ID.get();
                if (pidList.get(threadID)) {
                    System.out.println("Unregistering...");
                    pidList.set(threadID, false);
                    ID.set(-1);
                    numberOfRegistered--;
                    System.out.println("Unregister PID = " + threadID);
                }
                return threadID;
            }
        }
        return -1;
    }

    private int findPid() {
        if (numberOfRegistered < threadNumber) {
            for (int i = 0; i < threadNumber; ++i) {
                if (!pidList.get(i)) {
                    return i;
                }
            }
        }
        return -1;
    }

    private void reset() {
        synchronized (sync) {
            ID = new ThreadLocal<>();
            pidList = getFilledList(threadNumber, false);
        }
    }

    @Override
    public void lock() {}

    @Override
    public void unlock() {}

    public boolean tryLock() {
        return false;
    }

    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    public Condition newCondition() {
        throw new UnsupportedOperationException("Conditions are not supported");
    }

    public void lockInterruptibly() throws InterruptedException {
        throw new InterruptedException();
    }
}
