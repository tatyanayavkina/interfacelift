import javax.swing.text.AttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLDocument;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;

/**
 * Created by Татьяна on 08.07.2015.
 */
public class Wallpapers {
    private String webUrl;
    private String webPageMatch;
    private String imgMatch;
    private BlockingQueue<String> queue;
    private int threadCount;

    public Wallpapers(String aImgMatch, BlockingQueue<String> aQueue, int aThreadCount){
        webUrl = "http://www.hdwallpapers.in/";
        webPageMatch = ".html";
        imgMatch = aImgMatch;
        queue = aQueue;
        threadCount = aThreadCount;
    }

    public void makeDownloads (){
        makeDownloadQueue();
        for(int i = 0; i < threadCount; i++){
            new Thread(new ImageLoaderTask(queue, webUrl)).start();
        }
    }

    private void makeDownloadQueue () {
        HTMLDocument htmlDoc = HtmlParser.getHtmlDoc(webUrl);
        ArrayList<String> loadPageUrls = getLoadPageUrlList(htmlDoc);

        for (int i = 0; i < loadPageUrls.size(); i ++){
            String pageUrl = loadPageUrls.get(i);
            goToDownloadPage(pageUrl);
        }
    }

    private void goToDownloadPage(String pageUrl){
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
                queue.add(imgUrl);
            }
        }

    }

    //make list of pages which have href to image download
    private  ArrayList<String> getLoadPageUrlList(HTMLDocument htmlDoc){
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
