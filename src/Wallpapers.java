import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Wallpapers {
    final String rootUrl = "http://www.hdwallpapers.in";
    private final ThreadPool threadPool;

    public Wallpapers(int threadCount) {
        threadPool = new ThreadPool(threadCount);
    }

    public void prepare(int crawlingPageCount) {
        String pageUrl;

        for(int i = 1; i <= crawlingPageCount; ++i) {
            pageUrl =  rootUrl + "/latest_wallpapers/page/" + i;
            threadPool.addTask(new ImageLoadPageSearchTask(rootUrl, pageUrl, threadPool));
        }
    }

    public void execute() {
        threadPool.start();
        threadPool.join();
        threadPool.stop(); // there is possible error: a finish of the main thread may occur non planning interrupting pool threads
    }
}
