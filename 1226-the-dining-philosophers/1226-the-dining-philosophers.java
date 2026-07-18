import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

class DiningPhilosophers {
    
    // Five locks representing the five forks on the table
    private final ReentrantLock[] forks = new ReentrantLock[5];
    
    // A semaphore restricting at most 4 philosophers from attempting to eat simultaneously
    private final Semaphore tableLimit = new Semaphore(4);

    public DiningPhilosophers() {
        for (int i = 0; i < 5; i++) {
            forks[i] = new ReentrantLock();
        }
    }

    // Call the run() method of any runnable to execute its code.
    public void wantsToEat(int philosopher,
                           Runnable pickLeftFork,
                           Runnable pickRightFork,
                           Runnable eat,
                           Runnable putLeftFork,
                           Runnable putRightFork) throws InterruptedException {
        
        // Calculate fork indices relative to the philosopher's seat
        int leftForkNum = philosopher;
        int rightForkNum = (philosopher + 1) % 5;
        
        // Acquire a permit to step up to the table
        tableLimit.acquire();
        
        // Lock both adjacent forks
        forks[leftForkNum].lock();
        forks[rightForkNum].lock();
        
        try {
            // Execute the eating sequence actions
            pickLeftFork.run();
            pickRightFork.run();
            eat.run();
            putLeftFork.run();
            putRightFork.run();
        } finally {
            // Always unlock resources in a finally block to avoid deadlocks
            forks[rightForkNum].unlock();
            forks[leftForkNum].unlock();
            
            // Release the table permit for the next philosopher
            tableLimit.release();
        }
    }
}