//import java.util.concurrent.BlockingQueue;
//import java.util.concurrent.LinkedBlockingQueue;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.TimeUnit;

public class Wallpapers {
//    final String rootUrl = "http://www.hdwallpapers.in";
    final String rootUrl = "http://interfacelift.com";
    private final ThreadPool threadPool;

    public Wallpapers(int threadCount) {
        threadPool = new ThreadPool(threadCount);
    }

    public void prepare(int crawlingPageCount) {
        String pageUrl;

        for(int i = 1; i <= crawlingPageCount; ++i) {
//            pageUrl =  rootUrl + "/latest_wallpapers/page/" + i;
            pageUrl =  rootUrl + "/wallpaper/downloads/date/wide_16:9/1920x1080/index" + i + ".html";
//            threadPool.addTask(new ImageLoadPageSearchTask(rootUrl, pageUrl, threadPool));
            threadPool.addTask(new FindImageUrlTask(rootUrl, pageUrl, threadPool));
        }
    }

    public void execute() {
        threadPool.start();
        threadPool.join();
        threadPool.stop(); // there is a possible error: finishing of the main thread may occur non planning interrupting pool threads
    }
}
