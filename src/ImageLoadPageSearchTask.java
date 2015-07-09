import javax.swing.text.AttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLDocument;
import java.time.LocalTime;
import java.util.concurrent.BlockingQueue;

/**
 * Created by Татьяна on 09.07.2015.
 */
public class ImageLoadPageSearchTask implements Runnable{
    private BlockingQueue<String> pageQueue;
    private BlockingQueue<String> imageLoadPageQueue;
    private String webUrl;
    final String pageMatch = ".html";

    public ImageLoadPageSearchTask(BlockingQueue<String> aPageQueue, BlockingQueue<String> aImageLoadPageQueue,String aWebUrl){
        pageQueue = aPageQueue;
        imageLoadPageQueue = aImageLoadPageQueue;
        webUrl = aWebUrl;
    }

    public void run(){
        try{
            boolean done = false;

            while(!done){

                if (pageQueue.isEmpty()){
                    done = true;
                }
                else{
                    String pageUrl = pageQueue.take();
                    HTMLDocument htmlDoc = HtmlParser.getHtmlDoc(pageUrl);
                    findImageLoadPagesUrl(htmlDoc);
                }
            }

        }
        catch(InterruptedException ex){
        }
    }

    private void findImageLoadPagesUrl(HTMLDocument htmlDoc){
        for (HTMLDocument.Iterator iterator = htmlDoc.getIterator(HTML.Tag.A); iterator.isValid(); iterator.next()) {

            AttributeSet attributes = iterator.getAttributes();
            String imgLoadPageUrl = (String) attributes.getAttribute(HTML.Attribute.HREF);

            if (imgLoadPageUrl != null && imgLoadPageUrl.endsWith(pageMatch)) {
                String url;

                if(!imgLoadPageUrl.startsWith("http")){
                    url = webUrl + imgLoadPageUrl;
                } else{
                    url = imgLoadPageUrl;
                }
                imageLoadPageQueue.add(url);
            }
        }
    }

}
