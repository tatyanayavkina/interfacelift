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
import java.util.ArrayList;
//import javax.net.ssl.TrustManager;
//import javax.net.ssl.X509TrustManager;
//import javax.net.ssl.SSLContext;
//import javax.net.ssl.HttpsURLConnection;

public class Main {
    private static String webUrl = "http://www.hdwallpapers.in/";
    private static String webPageMatch = ".html";
    private static String imgMatch = "1920x1080.jpg";


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

        HTMLDocument htmlDoc = getHtmlDoc(webUrl);
        ArrayList<String> loadPageUrls = getLoadPageUrlList(htmlDoc);

        for (int i = 0; i < loadPageUrls.size(); i ++){
            String pageUrl = loadPageUrls.get(i);
            goToDownloadPage(pageUrl);
        }

//            for (HTMLDocument.Iterator iterator = htmlDoc.getIterator(HTML.Tag.IMG); iterator.isValid(); iterator.next()) {
//                AttributeSet attributes = iterator.getAttributes();
//                String imgSrc = (String) attributes.getAttribute(HTML.Attribute.SRC);
//                if (imgSrc != null && (imgSrc.endsWith(".jpg") || (imgSrc.endsWith(".png")) || (imgSrc.endsWith(".jpeg")) || (imgSrc.endsWith(".bmp")) || (imgSrc.endsWith(".ico")))) {
//                     downloadImage(imgSrc);
//                }
//            }

    }

    private static void goToDownloadPage(String pageUrl){
        String url;

        if(!pageUrl.startsWith("http")){
            url = webUrl + pageUrl;
        } else{
            url = pageUrl;
        }

        HTMLDocument htmlDoc = getHtmlDoc(url);

        for (HTMLDocument.Iterator iterator = htmlDoc.getIterator(HTML.Tag.A); iterator.isValid(); iterator.next()) {

            AttributeSet attributes = iterator.getAttributes();
            String imgUrl = (String) attributes.getAttribute(HTML.Attribute.HREF);

            if (imgUrl != null && imgUrl.endsWith(imgMatch)) {
                downloadImage(imgUrl);
            }
        }

    }

    //get content of web page with pageUrl url
    private static HTMLDocument getHtmlDoc(String pageUrl){
        // prepare need objects
        HTMLEditorKit htmlKit = new HTMLEditorKit();
        HTMLDocument htmlDoc = (HTMLDocument) htmlKit.createDefaultDocument();
        HTMLEditorKit.Parser parser = new ParserDelegator();
        HTMLEditorKit.ParserCallback callback = htmlDoc.getReader(0);

        try{
            //create url and open connection
            URL url = new URL(pageUrl);
            URLConnection connection =  url.openConnection();

            //create bufferedReader and read inputStream
            InputStream is = connection.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            //parse web page content
            parser.parse(br, callback, true);
        }
        catch(IOException e){
            e.printStackTrace();
        }

        return htmlDoc;
    }

    //make list of pages which have href to image download
    private static ArrayList<String> getLoadPageUrlList(HTMLDocument htmlDoc){
        ArrayList<String> loadPageUrls = new ArrayList<>();

        for (HTMLDocument.Iterator iterator = htmlDoc.getIterator(HTML.Tag.A); iterator.isValid(); iterator.next()) {

            AttributeSet attributes = iterator.getAttributes();
            String imgLoadPageUrl = (String) attributes.getAttribute(HTML.Attribute.HREF);

            if (imgLoadPageUrl != null && imgLoadPageUrl.endsWith(webPageMatch)) {
                loadPageUrls.add(imgLoadPageUrl);
            }
        }

        return loadPageUrls;
    }

    //download image
    private static void downloadImage(String imgUrl){
        BufferedImage image = null;
        String url;

        try {

            if (!(imgUrl.startsWith("http"))) {
                url = webUrl + imgUrl;
            } else {
                url = imgUrl;
            }

            System.out.println(url);

            imgUrl = imgUrl.substring(imgUrl.lastIndexOf("/") + 1);
            String imageFormat = imgUrl.substring(imgUrl.lastIndexOf(".") + 1);
            String imgPath = "D:/books/" + imgUrl + "";
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
