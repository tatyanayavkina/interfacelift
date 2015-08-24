
public class ImageLoaderTask  implements Runnable{

    private String imageUrl;

    public ImageLoaderTask(String aImageUrl){
        imageUrl = aImageUrl;
    }

    public void run() {
        ImageLoader.downloadImage(imageUrl);
    }
}
