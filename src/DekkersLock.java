import java.lang.reflect.Array;
import java.util.ArrayList;

public class DekkersLock extends AbstractFixnumLock {

    private volatile int turn = 0; // Id of thread

    protected ArrayList<VolBoolean> flag = getFilledList(threadNumber, new VolBoolean(false));

    DekkersLock() {
        super(2);
    }

    @Override
    public void lock() {
        int id = getId();

        flag.get(id).setValue(true);

        while(flag.get(getInvPid(id)).getValue()) {
            if (turn != getId()) {
                flag.get(id).setValue(false);
                flag.get(id).setValue(true);
            }
        }
    }

    @Override
    public void unlock() {
        int id = getId();
        flag.set(id, new VolBoolean(false));
        turn = getInvPid(id);
    }

    private static int getInvPid(int id) {
        return id ^ 1;
    }
}
