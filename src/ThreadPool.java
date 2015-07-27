import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Антон on 27.07.2015.
 */
public class ThreadPool {

    private final int nThreads;
    private PoolWorker[] threads;
    public BlockingQueue<Runnable> taskQueue = new LinkedBlockingQueue<>();

    public ThreadPool(int aThreads){
        nThreads = aThreads;
        threads = new PoolWorker[nThreads];

        for(i = 1; i < 4; i++){
            // переделать task
            taskQueue.add(new ImageLoadPageSearchTask(url, taskQueue));
        }

        for(int j = 0; j < nThreads; j++){
            threads[i] = new PoolWorker();
            threads[i].start();
        }

    }

    private class PoolWorker extends Thread{


        public void run() {
            Runnable r;

            while (true) {
                synchronized(taskQueue) {
                    while (taskQueue.isEmpty()) {
                        try
                        {
                            taskQueue.wait();
                        }
                        catch (InterruptedException ignored)
                        {
                        }
                    }

                    r = (Runnable) taskQueue.removeFirst();
                }

                // If we don't catch RuntimeException,
                // the pool could leak threads
                try {
                    r.run();
                }
                catch (RuntimeException e) {
                    // You might want to log something here
                }
            }
        }
    }

}
