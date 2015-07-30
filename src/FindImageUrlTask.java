import javax.swing.text.AttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLDocument;
import java.time.LocalTime;
import java.util.LinkedList;

/**
 * Created by Татьяна on 30.07.2015.
 */
public class FindImageUrlTask implements Runnable{
    private final LinkedList<Runnable> queue;
    private String webUrl;
    private String pageUrl;

    final String imgMatch = "1920x1080.jpg";

    public FindImageUrlTask(String aWebUrl, String aPageUrl, LinkedList<Runnable> aQueue){
        queue = aQueue;
        webUrl = aWebUrl;
        pageUrl = aPageUrl;
    }

    public void run(){
//        try{
        String imgUrl = findImageLoadUrl(pageUrl);
        if (imgUrl.length() > 0){
            synchronized(queue) {
                queue.addLast(new ImageLoaderTask(imgUrl));
                queue.notify();
            }
        }

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



