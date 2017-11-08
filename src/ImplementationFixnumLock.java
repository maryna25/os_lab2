import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public abstract class ImplementationFixnumLock implements FixnumLock {
    protected final static int threadNumber = 20;

    static ArrayList<Boolean> pidList = getFilledList(threadNumber, false);

    int pid = -1;

    private static Object sync = new Object();


    public static ArrayList<Boolean> getFilledList(int size, boolean value) {
        ArrayList<Boolean> list = new ArrayList<>();
        for(int i = 0; i < size; ++i) {
            list.add(value);
        }
        return list;
    }

    @Override
    public int getId() {
        return pid;
    }

    @Override
    public boolean register() {
        if(!findPid()) {
            return false;
        }
        pidList.set(pid, true);
        return true;
    }

    @Override
    public void unregister() {
        pidList.set(pid, false);
        resetPid();
    }

    private boolean findPid() {
        synchronized (sync) {
            for (int i = 0; i < threadNumber; ++i) {
                if (!pidList.get(i)) {
                    pid = i;
                    return true;
                }
            }

            return false;
        }
    }

    private void resetPid() {
        pid = -1;
    }


    public boolean tryLock() {
        return false;
    }

    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }
}
