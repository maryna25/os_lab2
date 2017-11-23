public class TestAbstractFixnumLock {
    public static TestFixnumLock fixnumLock = new TestFixnumLock();

    public class TestThread extends Thread {
        public void run() {
            int pid = fixnumLock.register();
        }
    }

    public class Test2Thread extends Thread {
        public void run() {
            fixnumLock.register();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e){}
            fixnumLock.unregister();
        }
    }

    public TestAbstractFixnumLock(){
        //testOverflow();
        //testDestroyNonexisting();
        //test();
        //testNThreads();
        testDoubleRegister();
    }

    public static void main(String[] args) {
        new TestAbstractFixnumLock();
    }

    public void testOverflow() {
        for(int i = 0; i < 21; i++){
            TestThread t = new TestThread();
            t.start();
        }
    }

    public static void testDestroyNonexisting() {
        int pid = fixnumLock.unregister();
        System.out.println(pid);
    }

    public static void test(){
        int pid = fixnumLock.register();
        pid = fixnumLock.getId();
        System.out.println("PID = " + pid);
        pid = fixnumLock.unregister();
    }

    public void testNThreads(){
        for(int i = 0; i < 20; i++){
            Test2Thread t = new Test2Thread();
            t.start();
        }
    }

    public static void testDoubleRegister(){
        fixnumLock.register();
        int pid = fixnumLock.getId();
        System.out.println("PID = " + pid);
        fixnumLock.register();
    }
}
