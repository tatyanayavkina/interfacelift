//import javax.net.ssl.TrustManager;
//import javax.net.ssl.X509TrustManager;
//import javax.net.ssl.SSLContext;
//import javax.net.ssl.HttpsURLConnection;

import java.time.LocalTime;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

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

        final int THREAD_COUNT = 10;

        BlockingQueue<String> queue = new LinkedBlockingQueue<>();
        Wallpapers wPapers = new Wallpapers(queue, THREAD_COUNT);

        System.out.println("start=" + LocalTime.now());
        wPapers.makeDownloads();
        System.out.println("end=" + LocalTime.now());
    }


}
