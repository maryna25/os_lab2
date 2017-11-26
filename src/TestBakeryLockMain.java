public class TestBakeryLockMain {
    public static void main(String[] args) {
//        ArrayList<BakeryLockTestThread> threads = new ArrayList<BakeryLockTestThread>;
//        for (int i=0; i<20; i++){
//            BakeryLockTestThread t1 = new BakeryLockTestThread(1);
//            threads.push(t1);
//
//        }
        BakeryLockTestThread t1 = new BakeryLockTestThread('+');
        BakeryLockTestThread t2 = new BakeryLockTestThread('-');
        BakeryLockTestThread t3 = new BakeryLockTestThread('+');
        BakeryLockTestThread t4 = new BakeryLockTestThread('-');
        BakeryLockTestThread t5 = new BakeryLockTestThread('+');
        BakeryLockTestThread t6 = new BakeryLockTestThread('-');

        while (true) {
            BakeryLockTestThread.getStaticCounter();
            System.out.println();
            t1.getOperationsNumber();
            t2.getOperationsNumber();
            t3.getOperationsNumber();
            t4.getOperationsNumber();
            t5.getOperationsNumber();
            t6.getOperationsNumber();
            System.out.println();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
