import javax.swing.text.AttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLDocument;
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

        HTMLDocument htmlDoc = HtmlParser.getHtmlDoc(webUrl);
        ArrayList<String> loadPageUrls = getLoadPageUrlList(htmlDoc);

        for (int i = 0; i < loadPageUrls.size(); i ++){
            String pageUrl = loadPageUrls.get(i);
            goToDownloadPage(pageUrl);
        }
    }

    private static void goToDownloadPage(String pageUrl){
        String url;

        if(!pageUrl.startsWith("http")){
            url = webUrl + pageUrl;
        } else{
            url = pageUrl;
        }

        HTMLDocument htmlDoc = HtmlParser.getHtmlDoc(url);

        for (HTMLDocument.Iterator iterator = htmlDoc.getIterator(HTML.Tag.A); iterator.isValid(); iterator.next()) {

            AttributeSet attributes = iterator.getAttributes();
            String imgUrl = (String) attributes.getAttribute(HTML.Attribute.HREF);

            if (imgUrl != null && imgUrl.endsWith(imgMatch)) {
                ImageLoader.downloadImage(webUrl,imgUrl);
            }
        }

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
}
