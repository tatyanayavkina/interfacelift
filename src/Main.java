import javax.imageio.ImageIO;
import javax.swing.text.AttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.parser.ParserDelegator;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
//import javax.net.ssl.TrustManager;
//import javax.net.ssl.X509TrustManager;
//import javax.net.ssl.SSLContext;
//import javax.net.ssl.HttpsURLConnection;

public class Main {

    public static void main(String[] args) {
//        // Create a new trust manager that trust all certificates
//        TrustManager[] trustAllCerts = new TrustManager[]{
//                new X509TrustManager() {
//                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
//                        return null;
//                    }
//                    public void checkClientTrusted(
//                            java.security.cert.X509Certificate[] certs, String authType) {
//                    }
//                    public void checkServerTrusted(
//                            java.security.cert.X509Certificate[] certs, String authType) {
//                    }
//                }
//        };
//
//// Activate the new trust manager
//        try {
//            SSLContext sc = SSLContext.getInstance("SSL");
//            sc.init(null, trustAllCerts, new java.security.SecureRandom());
//            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }



        String webUrl = "http://www.hdwallpapers.in/";
//        String webUrl = "http://www.goodfon.ru/";
//        String match = "1920_1080.jpg";
        System.out.println("before try");

        try{
            //create url and open connection
            URL url = new URL(webUrl);
            URLConnection connection =  url.openConnection();

            //create bufferedReader and read inputStream
            InputStream is = connection.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);


            //
            HTMLEditorKit htmlKit = new HTMLEditorKit();
            HTMLDocument htmlDoc = (HTMLDocument) htmlKit.createDefaultDocument();
            HTMLEditorKit.Parser parser = new ParserDelegator();
            HTMLEditorKit.ParserCallback callback = htmlDoc.getReader(0);
            parser.parse(br, callback, true);
            //iterate html document
            for (HTMLDocument.Iterator iterator = htmlDoc.getIterator(HTML.Tag.IMG); iterator.isValid(); iterator.next()) {
                AttributeSet attributes = iterator.getAttributes();
                String imgSrc = (String) attributes.getAttribute(HTML.Attribute.SRC);
                System.out.println("src=" + imgSrc);
                if (imgSrc != null && (imgSrc.endsWith(".jpg") || (imgSrc.endsWith(".png")) || (imgSrc.endsWith(".jpeg")) || (imgSrc.endsWith(".bmp")) || (imgSrc.endsWith(".ico")))) {
                     downloadImage(webUrl, imgSrc);
                }
            }
//            for(HTMLDocument.Iterator iterator = htmlDoc.getIterator(HTML.Tag.A); iterator.isValid(); iterator.next()) {
//                AttributeSet attributes = iterator.getAttributes();
//                String href = (String) attributes.getAttribute(HTML.Attribute.HREF);
//
//                if(href.contains(match)){
//                    downloadImage(webUrl, href);
//                }
//            }

        }
        catch(IOException e){
            e.printStackTrace();
        }

    }

    private static void goToDownloadPage(String webUrl, String pageUrl){
        String url;
        if(!pageUrl.startsWith("http")){
            url = webUrl + pageUrl;
        } else{
            url = pageUrl;
        }

    }

    private static void downloadImage(String url, String imgUrl){
        BufferedImage image = null;
        try {
            if (!(imgUrl.startsWith("http"))) {
                url = url + imgUrl;
            } else {
                url = imgUrl;
            }
            imgUrl = imgUrl.substring(imgUrl.lastIndexOf("/") + 1);
            String imageFormat = null;
            imageFormat = imgUrl.substring(imgUrl.lastIndexOf(".") + 1);
            String imgPath = null;
            imgPath = "D:/books/" + imgUrl + "";
            URL imageUrl = new URL(url);
            image = ImageIO.read(imageUrl);
            if (image != null) {
                File file = new File(imgPath);
                ImageIO.write(image, imageFormat, file);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
