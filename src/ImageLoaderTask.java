import javax.swing.text.AttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLDocument;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;

/**
 * Created by Tatiana on 09.07.2015.
 */
public class ImageLoaderTask  implements Runnable{

    private final LinkedList<Runnable> queue;
    private String webUrl;
    private String pageUrl;

    final String imgMatch = "1920x1080.jpg";

    public ImageLoaderTask(String aWebUrl, String aPageUrl, LinkedList<Runnable> aQueue){
        queue = aQueue;
        webUrl = aWebUrl;
        pageUrl = aPageUrl;
    }

    public void run(){
//        try{
            String imgUrl = findImageLoadUrl(pageUrl);
//                    System.out.println(LocalTime.now() + " IMGpage = " + imgPageUrl + " img=" + imgUrl);
            ImageLoader.downloadImage(imgUrl);

//        }
//        catch(InterruptedException ex){
//        }
    }

    private String findImageLoadUrl(String imgPageUrl){
        String url;
        String emptyUrl = "";
        HTMLDocument htmlDoc = HtmlParser.getHtmlDoc(imgPageUrl);
        for (HTMLDocument.Iterator iterator = htmlDoc.getIterator(HTML.Tag.A); iterator.isValid(); iterator.next()) {

            AttributeSet attributes = iterator.getAttributes();
            String imgUrl = (String) attributes.getAttribute(HTML.Attribute.HREF);

            if (imgUrl != null && imgUrl.endsWith(imgMatch)) {
                if(!imgUrl.startsWith("http")){
                    url = webUrl + imgUrl;
                } else{
                    url = imgUrl;
                }

                return url;
            }
        }

        return emptyUrl;
    }
}
