import java.util.ArrayList;

public class BakeryLock extends AbstractFixnumLock {

    private int threadNumber = 20;

    protected ArrayList<Integer> tickets = getFilledList(threadNumber, 0);
    protected ArrayList<Boolean> choosing = getFilledList(threadNumber, false);

    @Override
    public void lock()
    {
        choosing.set(getId(), true);
        int max = 0;
        for (int i = 0; i < threadNumber; i++) {
            if (tickets.get(i) > max) {
                max = tickets.get(i);
            }
        }
        // System.out.println(max);
        tickets.set(getId(), max + 1);
        choosing.set(getId(), false);



        for (int i = 0; i < tickets.size(); ++i) {
            if (i != getId()) {

                while (choosing.get(i)) {
                    Thread.yield();
                }
                while (!tickets.get(i).equals(0) && (tickets.get(getId()) > tickets.get(i) ||
                        (tickets.get(getId()).equals(tickets.get(i)) && getId() > i))) {
                    Thread.yield();
                }
            }
        }
        System.out.println("In the critical section: " + tickets.get(getId()));
    }

    public void unlock() {
        tickets.set(getId(), 0);
    }
}
