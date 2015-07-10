import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.time.LocalTime;

/**
 * Created by Татьяна on 08.07.2015.
 */
public class ImageLoader {
    private static String imgFolder = "D:/books/images/";

    //download image
    public static void downloadImage(String imgUrl){
        if (imgUrl.length() == 0){ return;}

        BufferedImage image = null;
        String url = imgUrl;
        String imgName;
        System.out.println(LocalTime.now() +" image from="+imgUrl);

        try {
            imgName = imgUrl.substring(imgUrl.lastIndexOf("/") + 1);
            String imageFormat = imgUrl.substring(imgUrl.lastIndexOf(".") + 1);

            URL imageUrl = new URL(url);
            image = ImageIO.read(imageUrl);

            if (image != null) {
                String imgPath = imgFolder + imgName + "";
                File file = new File(imgPath);
                ImageIO.write(image, imageFormat, file);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
