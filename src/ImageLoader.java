import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.BufferedInputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
//import java.time.LocalTime;
import javax.net.ssl.HttpsURLConnection;

public class ImageLoader {
    private static String imgFolder = "D:/books/images/";

    //download image
    public static void downloadImage(String urlStr){
        if (urlStr.length() == 0){ return;}



        String format = urlStr.substring(urlStr.lastIndexOf("."),urlStr.length());
        String fileName = urlStr.substring(urlStr.indexOf("_")+1,urlStr.lastIndexOf("_"))+format;
        System.out.print("downloading "+fileName+"");

        try {

            URL url = new URL(null, urlStr ,new sun.net.www.protocol.https.Handler());
            HttpsURLConnection httpConn = (HttpsURLConnection) url.openConnection();
            try {
                httpConn.setConnectTimeout(10000);
                httpConn.setReadTimeout(10000);
            } catch (Exception e) {
                System.out.println("downloading failed");
                return;
            }
            httpConn.setRequestProperty("User-Agent", "Mozilla 5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.8.0.11) ");
            InputStream inStream = httpConn.getInputStream();
            byte[] btImg = readInputStream(inStream);

            File file = new File(imgFolder + fileName);
            FileOutputStream fops = new FileOutputStream(file);
            fops.write(btImg);
            fops.flush();
            fops.close();
            System.out.print("\t\t\t\t\t successful \n");
        } catch (Exception e) {
            e.printStackTrace();
        }

//        try {
//            BufferedImage image = null;
//            String url = imgUrl;
//            String imgName;

//            imgName = imgUrl.substring(imgUrl.lastIndexOf("/") + 1);
//            String imageFormat = imgUrl.substring(imgUrl.lastIndexOf(".") + 1);
//            URL imageUrl = new URL(url);
//
//            image = ImageIO.read(imageUrl);
//
//            if (image != null) {
//                String imgPath = imgFolder + imgName + "";
//                File file = new File(imgPath);
//                ImageIO.write(image, imageFormat, file);
//            }
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
    }

    public static byte[] readInputStream(InputStream inStream) throws Exception{
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while( (len=inStream.read(buffer)) != -1 ){
            outStream.write(buffer, 0, len);
        }
        inStream.close();
        return outStream.toByteArray();
    }
}
