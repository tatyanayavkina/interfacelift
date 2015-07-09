import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Татьяна on 08.07.2015.
 */
public class Wallpapers {
    private String webUrl;
    private int threadCount;
    private BlockingQueue<String> pagesQueue = new LinkedBlockingQueue<>();

    public Wallpapers(int aThreadCount){
        webUrl = "http://www.hdwallpapers.in/";
        threadCount = aThreadCount;

        for(int i = 1; i < 4; i++){
            pagesQueue.add(webUrl + "/latest_wallpapers/page/" + i);
        }
    }

    public void makeDownloads (){
        BlockingQueue<String> imageLoadPageQueue = new LinkedBlockingQueue<>();

        for(int i = 0; i < threadCount; i++){
            new Thread(new ImageLoadPageSearchTask(pagesQueue, imageLoadPageQueue, webUrl)).start();
        }

        for(int i = 0; i < threadCount; i++){
            new Thread(new ImageLoaderTask(imageLoadPageQueue, webUrl)).start();
        }
    }

}
