import java.time.LocalTime;


/**
 * Created by Tatiana on 09.07.2015.
 */
public class ImageLoaderTask  implements Runnable{

    private String imageUrl;

    public ImageLoaderTask(String aImageUrl){
        imageUrl = aImageUrl;
    }

    public void run(){
//        try{
            ImageLoader.downloadImage(imageUrl);
            System.out.println("end=" + LocalTime.now());
//        }
//        catch(InterruptedException ex){
//        }
    }

}
