import javax.swing.text.AttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLDocument;
import java.time.LocalTime;
import java.util.LinkedList;

public class FindImageUrlTask implements Runnable{
    private final ThreadPool threadPool;
    private String webUrl;
    private String pageUrl;

    final String imgMatch = "1920x1080.jpg";

    public FindImageUrlTask(String aWebUrl, String aPageUrl, ThreadPool threadPool){
        webUrl = aWebUrl;
        pageUrl = aPageUrl;
        this.threadPool = threadPool;
    }

    public void run(){
        String imgUrl = findImageLoadUrl(pageUrl);
        if (imgUrl.length() > 0){
            threadPool.addTask(new ImageLoaderTask(imgUrl));
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



