import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


/**
 * Created by ������� on 08.07.2015.
 */
public class Wallpapers {
    private String webUrl;
    private int threadCount;
    private BlockingQueue<String> pagesQueue = new LinkedBlockingQueue<>();

    public Wallpapers(int aThreadCount){
        webUrl = "http://www.hdwallpapers.in";
        threadCount = aThreadCount;

        for(int i = 1; i < 4; i++){
            pagesQueue.add(webUrl + "/latest_wallpapers/page/" + i);
        }
    }

    public void makeDownloads (){
//        BlockingQueue<String> imageLoadPageQueue = new LinkedBlockingQueue<>();
//        ExecutorService es = Executors.newCachedThreadPool();
//
//        try{
//            for(int i=0;i<5;i++)
//                es.execute(new ImageLoadPageSearchTask(pagesQueue, imageLoadPageQueue, webUrl));
//            es.shutdown();
//            boolean finished = es.awaitTermination(1, TimeUnit.MINUTES);
//
//            if (finished){
//                for(int i = 0; i < threadCount; i++){
//                    new Thread(new ImageLoaderTask(imageLoadPageQueue, webUrl)).start();
//                }
//            }
//        }
//        catch(InterruptedException ex){}


//        for(int i = 0; i < threadCount; i++){
//            new Thread(new ImageLoadPageSearchTask(pagesQueue, imageLoadPageQueue, webUrl)).start();
//        }
//
//        for(int i = 0; i < threadCount; i++){
//            new Thread(new ImageLoaderTask(imageLoadPageQueue, webUrl)).start();
//        }
//
    }

}
