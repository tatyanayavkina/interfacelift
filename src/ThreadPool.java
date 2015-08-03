import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPool {
    private final int nThreads;
    private PoolWorker[] threads;
    private AtomicInteger nActiveThreads = new AtomicInteger();

    public final LinkedList<Runnable> taskQueue;

    private boolean continueExecution = true;
    final private Object threadWakeUp = new Object();

    final private Object threadPoolStopped = new Object();

    public ThreadPool(int aThreads) {
        nThreads = aThreads;
        threads = new PoolWorker[nThreads];
        taskQueue = new LinkedList<Runnable>();

        for(int j = 0; j < nThreads; j++){
            threads[j] = new PoolWorker(this);
        }
    }

    public void addTask(Runnable task) {
        synchronized (taskQueue) {
            taskQueue.addLast(task);
            synchronized (threadWakeUp) {
                threadWakeUp.notify();
            }
        }
    }

    public void start() {
        for(int j = 0; j < nThreads; j++){
            threads[j].start();
        }
    }

    public void stop() {
        continueExecution = false;
        synchronized (threadWakeUp) {
            threadWakeUp.notifyAll();
        }
    }

    private void finishHandle() {
        synchronized (threadPoolStopped) {
            threadPoolStopped.notifyAll();
        }
    }

    public void join() {
        try {
            synchronized(threadPoolStopped) {
                threadPoolStopped.wait();
            }
        } catch (InterruptedException exception) {
            // there is nothing to handle, so quit.
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private class PoolWorker extends Thread{
        private final ThreadPool parentPool;

        public PoolWorker(ThreadPool parentPool) {
            this.parentPool = parentPool;
        }

        // return a task or null (if there is no tasks to execute at the time)
        private Runnable tryTakeTask() {
            Runnable task = null;

            synchronized (taskQueue) {
                if (!taskQueue.isEmpty()) {
                    nActiveThreads.incrementAndGet();
                    return taskQueue.removeFirst();
                }

                if (nActiveThreads.get() == 0)
                    parentPool.finishHandle();
            }

            return task;
        }

        private void taskFinished() {
            nActiveThreads.decrementAndGet();
        }

        public void run() {
            Runnable task;

            while (continueExecution) {
                task = tryTakeTask();

                if (task == null) {
                    try {
                        synchronized (threadWakeUp) {
                            threadWakeUp.wait();
                        }
                    } catch (InterruptedException exception) {
                        // there is nothing to handle, so quit.
                    }

                    continue;
                }

                task.run();
                taskFinished();
            }
        }
    }
}
