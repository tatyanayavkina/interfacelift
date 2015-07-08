import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

/**
 * Created by Татьяна on 08.07.2015.
 */
public class ImageLoader {
    private static String imgFolder = "D:/books/";

    //download image
    public static void downloadImage(String webUrl, String imgUrl){
        BufferedImage image = null;
        String url;

        try {

            if (!(imgUrl.startsWith("http"))) {
                url = webUrl + imgUrl;
            } else {
                url = imgUrl;
            }

            imgUrl = imgUrl.substring(imgUrl.lastIndexOf("/") + 1);
            String imageFormat = imgUrl.substring(imgUrl.lastIndexOf(".") + 1);

            URL imageUrl = new URL(url);
            image = ImageIO.read(imageUrl);

            if (image != null) {
                String imgPath = imgFolder + imgUrl + "";
                File file = new File(imgPath);
                ImageIO.write(image, imageFormat, file);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
