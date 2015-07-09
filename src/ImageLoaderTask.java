import java.util.concurrent.BlockingQueue;

/**
 * Created by Татьяна on 09.07.2015.
 */
public class ImageLoaderTask  implements Runnable{

    private BlockingQueue<String> queue;
    private String webUrl;

    public ImageLoaderTask(BlockingQueue<String> aQueue, String aWebUrl){
        queue = aQueue;
        webUrl = aWebUrl;
    }

    public void run(){
        try{
            boolean done = false;

            while(!done){

                if (queue.isEmpty()){
                    done = true;
                }
                else{
                    String imgUrl = queue.take();
                    ImageLoader.downloadImage(webUrl,imgUrl);
                }
            }

        }
        catch(InterruptedException ex){
        }
    }
}
