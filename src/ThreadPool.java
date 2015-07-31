import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Антон on 27.07.2015.
 */
public class ThreadPool {

    private final int nThreads;
    private PoolWorker[] threads;
    public final LinkedList<Runnable> taskQueue;
    private volatile AtomicInteger nActiveThreads = new AtomicInteger();
    private boolean isStopped = false;
    private String webUrl = "http://www.hdwallpapers.in";

    public ThreadPool(int aThreads){
        nThreads = aThreads;
        threads = new PoolWorker[nThreads];
        taskQueue = new LinkedList<>();

        for(int i = 1; i < 4; i++){
            // переделать task
            String url = webUrl + "/latest_wallpapers/page/" + i;
            taskQueue.addLast(new ImageLoadPageSearchTask(webUrl, url, taskQueue));
        }

        for(int j = 0; j < nThreads; j++){
            threads[j] = new PoolWorker();
            threads[j].start();
        }

    }

    public void kill(){
        isStopped = true;
    }

    private class PoolWorker extends Thread{


        public void run() {
            Runnable r;

            while (!isStopped) {
                synchronized(taskQueue) {
                    while (taskQueue.isEmpty()) {
                        try
                        {
                            int active = nActiveThreads.decrementAndGet();
                            if(active == 0){return;}
                            taskQueue.wait();
                        }
                        catch (InterruptedException ignored)
                        {
                        }
                    }

                    r = taskQueue.removeFirst();
                    nActiveThreads.incrementAndGet();
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
