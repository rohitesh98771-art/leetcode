class H2O {
    private Semaphore h = new Semaphore(2);
    private Semaphore o = new Semaphore(0);
    public H2O() {}
    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        h.acquire();
        releaseHydrogen.run();
        if (h.availablePermits() == 0)
            o.release();
    }
    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        o.acquire();
        releaseOxygen.run();
        h.release(2);
    }
}