/**
 * Created by Татьяна on 17.08.2015.
 */
public class Constant {
   public static final String EMPTY_STRING = "";

   public static final String HTTP_PROTOCOL = "http";
   public static final String HTTPS_PROTOCOL = "https";

   public static final String HTML_A_HREF_TAG_PATTERN = "<a\\s+(?:[^>]*?\\s+)?href=\"([^\"]*)\"";

   public enum webSite {
      HD_WALLPAPERS(1, "http://www.hdwallpapers.in", HTTP_PROTOCOL),
      INTERFACELIFT(2, "http://interfacelift.com", HTTP_PROTOCOL);

      private int code;
      private String host;
      private String protocol;

      webSite(String host, String protocol, int code){
         this.host = host;
         this.protocol = protocol;
         this.code = code;
      }

      public  int getCode(){
         return code;
      }

      public String getHost(){
         return host;
      }

      public String getProtocol(){
         return protocol;
      }

      public static webSite getByCode(int code){
         webSite site;

         switch(code){
            case 1:
               site = HD_WALLPAPERS;
               break;
            case 2:
               site = INTERFACELIFT;
               break;
            default:
               site = HD_WALLPAPERS;
               break;
         }

         return site;
      }

   }
}
