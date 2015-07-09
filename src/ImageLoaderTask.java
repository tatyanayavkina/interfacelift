import javax.swing.text.AttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLDocument;
import java.util.concurrent.BlockingQueue;

/**
 * Created by Татьяна on 09.07.2015.
 */
public class ImageLoaderTask  implements Runnable{

    private BlockingQueue<String> queue;
    private String webUrl;
    final String imgMatch = "1920x1080.jpg";

    public ImageLoaderTask(BlockingQueue<String> aQueue, String aWebUrl){
        queue = aQueue;
        webUrl = aWebUrl;
    }

    public void run(){
        try{
            boolean done = false;

            while(!done){

                if (queue.isEmpty()){
                    done = true;
                }
                else{
                    String imgPageUrl = queue.take();
                    String imgUrl = findImageLoadUrl(imgPageUrl);
                    ImageLoader.downloadImage(imgUrl);
                }
            }

        }
        catch(InterruptedException ex){
        }
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
