//import javax.net.ssl.TrustManager;
//import javax.net.ssl.X509TrustManager;
//import javax.net.ssl.SSLContext;
//import javax.net.ssl.HttpsURLConnection;

import java.time.LocalTime;

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

        final int THREAD_COUNT = 6;
        Wallpapers wPapers = new Wallpapers(THREAD_COUNT);
        wPapers.prepare(3);
        wPapers.execute();
    }
}
