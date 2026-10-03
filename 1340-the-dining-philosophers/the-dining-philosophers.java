class DiningPhilosophers {
    private final Semaphore[] forks = new Semaphore[5];
    public DiningPhilosophers() {
        for (int i = 0; i < 5; i++)
            forks[i] = new Semaphore(1);
    }
    public void wantsToEat(int philosopher,
    Runnable pickLeftFork,
    Runnable pickRightFork,
    Runnable eat,
    Runnable putLeftFork,
    Runnable putRightFork) throws InterruptedException {
        int left = philosopher;
        int right = (philosopher + 1) % 5;

        if (philosopher % 2 == 0) {
            forks[left].acquire();
            forks[right].acquire();
        } else {
            forks[right].acquire();
            forks[left].acquire();
        }
        pickLeftFork.run();
        pickRightFork.run();
        eat.run();
        putLeftFork.run();
        putRightFork.run();
        forks[left].release();
        forks[right].release();
    }
}