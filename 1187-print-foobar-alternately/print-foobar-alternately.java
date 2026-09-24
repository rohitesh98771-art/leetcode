class FooBar {
    private int n;
    private boolean foo = true;
    public FooBar(int n) {
        this.n = n;
    }
    public synchronized void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            while (!foo) wait();
            printFoo.run();
            foo = false;
            notify();
        }
    }

    public synchronized void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            while (foo) wait();
            printBar.run();
            foo = true;
            notify();
        }
    }
}