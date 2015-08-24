/**
 * Created by Татьяна on 17.08.2015.
 */
public class Constant {
   public static final String EMPTY_STRING = "";

   public static final String HTTP_PROTOCOL = "http";
   public static final String HTTPS_PROTOCOL = "https";

   public static final String HTML_A_HREF_TAG_PATTERN = "<a\\s+(?:[^>]*?\\s+)?href=\"([^\"]*)\"";

   public enum webSite {
      HD_WALLPAPERS("http://www.hdwallpapers.in", HTTP_PROTOCOL),
      INTERFACELIFT("http://interfacelift.com", HTTP_PROTOCOL);

      private String host;
      private String protocol;

      webSite(String host, String protocol){
         this.host = host;
         this.protocol = protocol;
      }

      public String getHost(){
         return host;
      }

      public String getProtocol(){
         return protocol;
      }

   }
}
