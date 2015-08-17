import java.time.LocalTime;

public class ImageLoaderTask  implements Runnable{

    private String imageUrl;

    public ImageLoaderTask(String aImageUrl){
        imageUrl = aImageUrl;
    }

    public void run() {

        ImageLoader.downloadImage(imageUrl);
        System.out.println("url=" + imageUrl);
        System.out.println("end=" + LocalTime.now());
    }
}
