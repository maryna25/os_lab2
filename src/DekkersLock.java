import java.util.ArrayList;

public class DekkersLock extends AbstractFixnumLock {

    static int threadNumber = 2;

    private static int turn = 0; // Id of thread

    protected ArrayList<Boolean> flag = getFilledList(threadNumber, false);

    @Override
    public void lock() {
        flag.set(getId(), true);

        while(flag.get(getInvPid())) {

            if (turn != getId()) {
                flag.set(getId(), false);

                // Teacher said to comment that
                //while (turn != getId()) { Thread.yield(); }

                flag.set(getId(), true);
            }
        }
    }

    @Override
    public void unlock() {
        flag.set(getId(), false);
        turn = getInvPid();
    }

    private int getInvPid() {
        return getId() ^ 1;
    }
}
