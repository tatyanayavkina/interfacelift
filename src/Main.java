import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class Main {
    public static void main(String[] args) {

//        final int THREAD_COUNT = 6;
//        Wallpapers wPapers = new Wallpapers(THREAD_COUNT);
//        wPapers.prepare(3);
//        wPapers.execute();

        String imgFolder = "D:/books/images/";
        String imgUrl = "http://interfacelift.com/wallpaper/D1c18a88/03941_rumkale_1920x1080.jpg";
        String imgName = imgUrl.substring(imgUrl.lastIndexOf("/") + 1);
        String imageFormat = imgUrl.substring(imgUrl.lastIndexOf(".") + 1);

        File file = new File(imgFolder + imgName);

        try {
            URLConnection conn = new URL(imgUrl).openConnection();
            conn.connect();

            InputStream in = conn.getInputStream();
            OutputStream out = new FileOutputStream(file);

            int b = 0;

            while (b != -1) {
                b = in.read();

                if (b != -1)
                    out.write(b);
            }

            out.close();
            in.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
