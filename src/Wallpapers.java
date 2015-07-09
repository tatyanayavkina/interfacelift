import javax.swing.text.AttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLDocument;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;

/**
 * Created by Татьяна on 08.07.2015.
 */
public class Wallpapers {
    private String webUrl;
    private BlockingQueue<String> queue;
    private int threadCount;

    public Wallpapers(BlockingQueue<String> aQueue, int aThreadCount){
        webUrl = "http://www.hdwallpapers.in/";
        queue = aQueue;
        threadCount = aThreadCount;
    }

    public void makeDownloads (){


        for(int i = 0; i < threadCount; i++){
            new Thread(new ImageLoaderTask(queue, webUrl)).start();
        }
    }
}
